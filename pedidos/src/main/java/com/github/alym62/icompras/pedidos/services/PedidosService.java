package com.github.alym62.icompras.pedidos.services;

import com.github.alym62.icompras.pedidos.client.representation.ClienteRepresentation;
import com.github.alym62.icompras.pedidos.client.representation.ProdutoRepresentation;
import com.github.alym62.icompras.pedidos.domain.DadosPagamento;
import com.github.alym62.icompras.pedidos.domain.ItemPedidoPersistence;
import com.github.alym62.icompras.pedidos.domain.PedidoPersistence;
import com.github.alym62.icompras.pedidos.domain.enums.StatusPedido;
import com.github.alym62.icompras.pedidos.domain.enums.TipoPagamento;
import com.github.alym62.icompras.pedidos.exceptions.NotFoundException;
import com.github.alym62.icompras.pedidos.exceptions.ValidationException;
import com.github.alym62.icompras.pedidos.integrations.ClienteIntegration;
import com.github.alym62.icompras.pedidos.integrations.ProdutoIntegration;
import com.github.alym62.icompras.pedidos.integrations.StripeIntegration;
import com.github.alym62.icompras.pedidos.repositories.PedidosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PedidosService {
    private final PedidosRepository pedidosRepository;
    private final ItemPedidosService itemPedidosService;
    private final StripeIntegration stripeClient;
    private final ProdutoIntegration produtosClient;
    private final ClienteIntegration clientesClient;

    @Transactional
    public PedidoPersistence criarPedido(PedidoPersistence pedido) {
        pedido.setStatus(StatusPedido.REALIZADO);
        pedido.setDataPedido(LocalDateTime.now());

        BigDecimal total = calcularTotalDoPedido(pedido.getItens());
        pedido.setTotal(total);

        popularPedido(pedido);

        validarPedido(pedido);

        pedido = pedidosRepository.save(pedido);
        itemPedidosService.salvarTodosItensDoPedido(pedido.getItens());

        String chaveDePagamentoStripe = stripeClient.enviarPagamento(pedido);
        if (StringUtils.hasText(chaveDePagamentoStripe)) {
            pedido.setChavePagamento(chaveDePagamentoStripe);
        }

        return pedido;
    }

    public PedidoPersistence obterPedidoPorCodigo(Long codigo) {
        return pedidosRepository.findById(codigo)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado"));
    }

    public boolean pedidoExisteComCodigoEChaveDePagamento(Long codigo, String chaveDePagamento) {
        return pedidosRepository.existsByCodigoAndChavePagamento(codigo, chaveDePagamento);
    }

    @Transactional
    public void atualizarStatusDoPedido(Long codigoDoPedido, StatusPedido statusDoPedido, String algumaObservacao, String chaveDePagamento) {
        pedidosRepository.atualizarStatusDePagamentoPorCodigoAndChaveDePagamento(statusDoPedido, algumaObservacao, codigoDoPedido, chaveDePagamento);
    }

    @Transactional
    public void adicionarNovoPagamentoParaPedido(Long codigoDoPedido, String dadoPagamento, TipoPagamento tipoPagamento) {
        final PedidoPersistence pedidoExistente = obterPedidoPorCodigo(codigoDoPedido);
        if (!StatusPedido.ERRO_PAGAMENTO.getStatus().equals(pedidoExistente.getStatus().getStatus())) {
            throw new ValidationException("Ops! Não é possível atualizar um pagamento que não esteja com erro", "status");
        }

        final DadosPagamento novoDadosDePagamento = new DadosPagamento();
        novoDadosDePagamento.setDadosPagamento(dadoPagamento);
        novoDadosDePagamento.setTipoPagamento(tipoPagamento);

        pedidoExistente.setDadosDePagamento(novoDadosDePagamento);
        pedidoExistente.setStatus(StatusPedido.REALIZADO);
        pedidoExistente.setObservacoes("AGUARDANDO PROCESSAMENTO");

        String chaveDePagamentoStripe = stripeClient.enviarPagamento(pedidoExistente);
        if (StringUtils.hasText(chaveDePagamentoStripe)) {
            pedidoExistente.setChavePagamento(chaveDePagamentoStripe);
        }

        pedidosRepository.save(pedidoExistente);
    }

    private BigDecimal calcularTotalDoPedido(Set<ItemPedidoPersistence> itensDoPedido) {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemPedidoPersistence item : itensDoPedido) {
            total = item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())).add(total).abs();
        }

        return total;
    }

    private void popularPedido(PedidoPersistence pedido) {
        pedido.getItens().forEach(item -> item.setPedido(pedido));
    }

    private void validarPedido(PedidoPersistence pedido) {
        validarSeClienteExiste(pedido.getCodigoCliente());
        validarSeProdutoExiste(pedido.getItens());
    }

    private void validarSeProdutoExiste(final Set<ItemPedidoPersistence> itensDoPedido) {
        itensDoPedido.forEach(item -> {
            ProdutoRepresentation produtoExiste = produtosClient.obterProdutoNoMs(item.getCodigoProduto());
            if (Objects.isNull(produtoExiste)) {
                throw new NotFoundException("Produto indisponível ou não cadastrado no sistema");
            }
        });
    }

    private void validarSeClienteExiste(final Long codigoDoCliente) {
        ClienteRepresentation clienteExiste = clientesClient.obterClienteNoMs(codigoDoCliente);
        if (Objects.isNull(clienteExiste)) {
            throw new NotFoundException("Cliente indisponível ou não cadastrado no sistema");
        }
    }
}
