package com.github.alym62.icompras.pedidos.services;

import com.github.alym62.icompras.pedidos.domain.enums.StatusPedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecebimentoPagamentoService {
    private final PedidosService pedidosService;

    public void atualizarPagamento(Long codigo, String chaveDePagamento, boolean sucessoDoPagamento, String observacoes) {
        boolean pedidoExistente = pedidosService.pedidoExisteComCodigoEChaveDePagamento(codigo, chaveDePagamento);

        if (!pedidoExistente) {
            log.error("[Webhook - Pedidos] -> Pedido não encontrado: codigo {} - chave de pagamento {}", codigo, chaveDePagamento);
            return;
        }

        if (sucessoDoPagamento) {
            pedidosService.atualizarStatusDoPedido(codigo, StatusPedido.PAGO, null, chaveDePagamento);
            return;
        }

        pedidosService.atualizarStatusDoPedido(codigo, StatusPedido.ERRO_PAGAMENTO, observacoes, chaveDePagamento);
    }
}
