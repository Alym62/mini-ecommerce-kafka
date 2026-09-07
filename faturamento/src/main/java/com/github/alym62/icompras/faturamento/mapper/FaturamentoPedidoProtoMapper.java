package com.github.alym62.icompras.faturamento.mapper;

import com.github.alym62.icompras.faturamento.FaturamentoPedidoProto;
import com.github.alym62.icompras.faturamento.StatusPedidoProto;
import org.springframework.stereotype.Component;

@Component
public class FaturamentoPedidoProtoMapper {
    public FaturamentoPedidoProto.FaturamentoDoPedido pedidoInputToProto(Long codigoDoPedido, String urlNF) {
        return FaturamentoPedidoProto.FaturamentoDoPedido.newBuilder()
                .setCodigoPedido(codigoDoPedido)
                .setUrlNotaFiscal(urlNF)
                .setStatusDoPedido(StatusPedidoProto.StatusPedido.FATURADO)
                .build();
    }
}
