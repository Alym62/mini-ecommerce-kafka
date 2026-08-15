package com.github.alym62.icompras.pedidos.publisher.representation;

import com.github.alym62.icompras.pedidos.domain.enums.StatusPedido;

import java.math.BigDecimal;
import java.util.Set;

public record DetalhePedidoRepresentation(
        Long codigoPedido,
        Long codigoCliente,
        String nome,
        String cpf,
        String logradouro,
        String bairro,
        String email,
        String telefone,
        String dataPedido,
        BigDecimal total,
        StatusPedido statusDoPedido,
        Set<DetalheItemPedidoRepresentation> itens
) {
}
