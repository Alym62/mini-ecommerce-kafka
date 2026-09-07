package com.github.alym62.icompras.pedidos.mappers.proto;

import com.github.alym62.icompras.pedidos.FaturamentoPedidoProto;
import com.github.alym62.icompras.pedidos.domain.enums.StatusPedido;
import com.github.alym62.icompras.pedidos.input.PedidoFaturadoInput;
import org.springframework.stereotype.Component;

@Component
public class PedidoFaturadoProtoMapper {

    public PedidoFaturadoInput toInput(FaturamentoPedidoProto.FaturamentoDoPedido pedidoFaturado, String codigoDeRastreio) {
        return new PedidoFaturadoInput(
                pedidoFaturado.getCodigoPedido(),
                StatusPedido.valueOf(pedidoFaturado.getStatusDoPedido().name()),
                pedidoFaturado.getUrlNotaFiscal(),
                codigoDeRastreio
        );
    }
}
