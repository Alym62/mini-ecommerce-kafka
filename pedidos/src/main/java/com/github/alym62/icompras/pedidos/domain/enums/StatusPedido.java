package com.github.alym62.icompras.pedidos.domain.enums;

import lombok.Getter;

@Getter
public enum StatusPedido {
    REALIZADO("REALIAZDO"),
    PAGO("PAGO"),
    FATURADO("FATURADO"),
    ENVIADO("ENVIADO"),
    ERRO_PAGAMENTO("ERRO_PAGAMENTO"),
    PREPARANDO_ENVIO("PREPARANDO_ENVIO");

    private final String status;

    StatusPedido(final String status) {
        this.status = status;
    }
}
