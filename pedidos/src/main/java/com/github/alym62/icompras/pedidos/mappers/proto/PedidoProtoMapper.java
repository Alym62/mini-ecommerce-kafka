package com.github.alym62.icompras.pedidos.mappers.proto;

import com.github.alym62.icompras.pedidos.PedidoProto;
import com.github.alym62.icompras.pedidos.domain.PedidoPersistence;
import org.springframework.stereotype.Component;

@Component
public class PedidoProtoMapper {

    public PedidoProto.Pedido toProto(PedidoPersistence pedido) {
        var itensProto = pedido.getItens().stream()
                .map(item -> PedidoProto.DetalheItemPedido.newBuilder()
                        .setCodigoProduto(item.getCodigoProduto())
                        .setNome(item.getDetalheDoItem().nome())
                        .setQuantidade(item.getQuantidade())
                        .setValorUnitario(item.getValorUnitario().toString())
                        .build())
                .toList();

        return PedidoProto.Pedido.newBuilder()
                .setCodigoPedido(pedido.getCodigo())
                .setCodigoCliente(pedido.getCodigoCliente())
                .setNome(pedido.getDetalhesDoPedido().nome())
                .setCpf(pedido.getDetalhesDoPedido().cpf())
                .setLogradouro(pedido.getDetalhesDoPedido().logradouro())
                .setBairro(pedido.getDetalhesDoPedido().bairro())
                .setEmail(pedido.getDetalhesDoPedido().email())
                .setTelefone(pedido.getDetalhesDoPedido().telefone())
                .setDataPedido(pedido.getDataPedido().toString())
                .setTotal(pedido.getTotal().toString())
                .setStatusDoPedido(PedidoProto.StatusPedido.valueOf(pedido.getStatus().name()))
                .addAllItens(itensProto)
                .build();
    }
}
