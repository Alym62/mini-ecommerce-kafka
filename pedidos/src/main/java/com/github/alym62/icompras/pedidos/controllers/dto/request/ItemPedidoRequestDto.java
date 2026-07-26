package com.github.alym62.icompras.pedidos.controllers.dto.request;

import java.math.BigDecimal;

public record ItemPedidoRequestDto(
        Long codigoProduto,
        Integer quantidade,
        BigDecimal valorUnitario
) {
}
