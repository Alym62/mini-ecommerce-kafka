package com.github.alym62.icompras.pedidos.services;

import com.github.alym62.icompras.pedidos.domain.PedidoPersistence;
import com.github.alym62.icompras.pedidos.domain.enums.StatusPedido;
import com.github.alym62.icompras.pedidos.publisher.PagamentoPusblisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecebimentoPagamentoService {
    private final PedidosService pedidosService;
    private final PagamentoPusblisher pagamentoPusblisher;

    public void atualizarPagamento(Long codigo, String chaveDePagamento, boolean sucessoDoPagamento, String observacoes) {
        boolean pedidoExistente = pedidosService.pedidoExisteComCodigoEChaveDePagamento(codigo, chaveDePagamento);

        if (!pedidoExistente) {
            log.error("[Webhook - Pedidos] -> Pedido não encontrado: codigo {} - chave de pagamento {}", codigo, chaveDePagamento);
            return;
        }

        if (sucessoDoPagamento) {
            pedidosService.atualizarStatusDoPedido(codigo, StatusPedido.PAGO, null, chaveDePagamento);

            PedidoPersistence carregarPedidoComDetalhes = pedidosService.obterPedidoComDadosCompletos(codigo);
            pagamentoPusblisher.publicarPedido(carregarPedidoComDetalhes);

            return;
        }

        pedidosService.atualizarStatusDoPedido(codigo, StatusPedido.ERRO_PAGAMENTO, observacoes, chaveDePagamento);
    }
}
