package com.github.alym62.icompras.controller.dto.request;

import java.math.BigDecimal;

public record ProdutoRequestDto(
        String nome,
        BigDecimal valorUnitario
) {
}
