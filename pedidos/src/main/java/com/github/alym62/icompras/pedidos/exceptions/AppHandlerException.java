package com.github.alym62.icompras.pedidos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppHandlerException {
    @ExceptionHandler(ValidationException.class)
    public ProblemDetail validationException(ValidationException exception) {
        final ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.BAD_GATEWAY);
        pb.setTitle("Ops! Ocorreu um erro ao tentar buscar informações externas");
        pb.setDetail(exception.getMessage() + " " + exception.getField());
        return pb;
    }

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail notFoundException(NotFoundException exception) {
        final ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("Ops! Não foi possível encontrar as seguintes informações");
        pb.setDetail(exception.getMessage());
        return pb;
    }
}
