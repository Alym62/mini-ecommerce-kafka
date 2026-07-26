package com.github.alym62.icompras.controller.dto.response;

import java.math.BigDecimal;

public record ProdutoResponseDto(
        String nome,
        BigDecimal valorUnitario
) {
}
