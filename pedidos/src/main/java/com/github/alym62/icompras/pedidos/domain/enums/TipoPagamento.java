package com.github.alym62.icompras.pedidos.domain.enums;

import lombok.Getter;

@Getter
public enum TipoPagamento {
    CREDITO("CREDITO"),
    PIX("PIX");

    private final String tipoPagamento;

    TipoPagamento(final String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
}
