package com.github.alym62.icompras.pedidos.controllers.dto.request;

public record RecebimentoPagamentoRequestDto(
        Long codigo,
        String chavePagamento,
        boolean status,
        String observacoes
) {
}
