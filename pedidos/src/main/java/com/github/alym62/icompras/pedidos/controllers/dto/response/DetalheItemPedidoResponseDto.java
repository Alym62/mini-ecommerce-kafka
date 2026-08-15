package com.github.alym62.icompras.pedidos.controllers.dto.response;

import java.math.BigDecimal;

public record DetalheItemPedidoResponseDto(
        Long codigoProduto,
        String nome,
        Integer quantidade,
        BigDecimal valorUnitario
) {
}
