package com.github.alym62.icompras.faturamento.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppHandlerException {
    @ExceptionHandler(UploadDeArquivoException.class)
    public ProblemDetail validationException(UploadDeArquivoException exception) {
        final ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("Ops! Ocorreu um erro ao tentar realizar upload de arquivo");
        pb.setDetail(exception.getMessage());
        return pb;
    }
}
