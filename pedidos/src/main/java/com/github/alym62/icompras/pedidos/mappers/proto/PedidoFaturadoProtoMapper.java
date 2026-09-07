package com.github.alym62.icompras.pedidos.mappers.proto;

import com.github.alym62.icompras.pedidos.FaturamentoPedidoProto;
import com.github.alym62.icompras.pedidos.PedidoEnviadoProto;
import com.github.alym62.icompras.pedidos.domain.enums.StatusPedido;
import com.github.alym62.icompras.pedidos.input.PedidoFaturadoInput;
import org.springframework.stereotype.Component;

@Component
public class PedidoFaturadoProtoMapper {

    public PedidoFaturadoInput toInput(FaturamentoPedidoProto.FaturamentoDoPedido pedidoFaturado) {
        return new PedidoFaturadoInput(
                pedidoFaturado.getCodigoPedido(),
                StatusPedido.valueOf(pedidoFaturado.getStatusDoPedido().name()),
                pedidoFaturado.getUrlNotaFiscal(),
                null
        );
    }

    public PedidoFaturadoInput toInput(PedidoEnviadoProto.PedidoEnviado pedidoEnviado) {
        return new PedidoFaturadoInput(
                pedidoEnviado.getCodigoPedido(),
                StatusPedido.valueOf(pedidoEnviado.getStatusDoPedido().name()),
                null,
                pedidoEnviado.getCodigoRastreio()
        );
    }
}
