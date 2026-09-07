package com.github.alym62.icompras.faturamento.mapper;

import com.github.alym62.icompras.faturamento.PedidoProto;
import com.github.alym62.icompras.faturamento.input.ClienteInput;
import com.github.alym62.icompras.faturamento.input.ItemPedidoInput;
import com.github.alym62.icompras.faturamento.input.PedidoInput;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PedidoProtoMapper {
    public PedidoInput protoToPedidoInput(PedidoProto.Pedido proto) {
        ClienteInput cliente = new ClienteInput(
                proto.getNome(), proto.getCpf(), proto.getLogradouro(), null, proto.getBairro(), proto.getEmail(),
                proto.getTelefone()
        );

        Set<ItemPedidoInput> itens = proto.getItensList()
                .stream()
                .map(this::protoToItemPedido)
                .collect(Collectors.toSet());

        return new PedidoInput(proto.getCodigoPedido(), cliente, proto.getDataPedido(), new BigDecimal(proto.getTotal()), itens);
    }

    private ItemPedidoInput protoToItemPedido(PedidoProto.DetalheItemPedido proto) {
        BigDecimal valorUnitario = new BigDecimal(proto.getValorUnitario());
        return new ItemPedidoInput(
                proto.getCodigoProduto(), proto.getNome(), valorUnitario, proto.getQuantidade(),
                valorUnitario.multiply(BigDecimal.valueOf(proto.getQuantidade())).abs()
        );
    }
}
