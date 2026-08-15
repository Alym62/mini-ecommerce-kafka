package com.github.alym62.icompras.pedidos.publisher.representation;

import java.math.BigDecimal;

public record DetalheItemPedidoRepresentation(
        Long codigoProduto,
        String nome,
        Integer quantidade,
        BigDecimal valorUnitario
) {
}
