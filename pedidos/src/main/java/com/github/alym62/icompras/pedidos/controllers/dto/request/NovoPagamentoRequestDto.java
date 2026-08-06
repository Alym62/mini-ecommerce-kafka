package com.github.alym62.icompras.pedidos.controllers.dto.request;

import com.github.alym62.icompras.pedidos.domain.enums.TipoPagamento;

public record NovoPagamentoRequestDto(
        String dadosDePagamento,
        TipoPagamento tipoDePagamento
) {
}
