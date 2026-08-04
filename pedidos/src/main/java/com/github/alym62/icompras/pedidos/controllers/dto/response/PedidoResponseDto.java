package com.github.alym62.icompras.pedidos.controllers.dto.response;

import com.github.alym62.icompras.pedidos.domain.enums.StatusPedido;

import java.math.BigDecimal;

public record PedidoResponseDto(
        String chaveDePagamento,
        StatusPedido status,
        BigDecimal total
) {
}
