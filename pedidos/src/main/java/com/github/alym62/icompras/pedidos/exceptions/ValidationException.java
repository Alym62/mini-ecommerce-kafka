package com.github.alym62.icompras.pedidos.exceptions;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {
    private final String field;
    private final String message;

    public ValidationException(String message, String field) {
        super(message);
        this.message = message;
        this.field = field;
    }
}
