package com.github.alym62.icompras.faturamento.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppHandlerException {
    @ExceptionHandler(UploadDeArquivoException.class)
    public ProblemDetail uploadException(UploadDeArquivoException exception) {
        final ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("Ops! Ocorreu um erro ao tentar realizar upload de arquivo");
        pb.setDetail(exception.getMessage());
        return pb;
    }

    @ExceptionHandler(ArquivoNotFound.class)
    public ProblemDetail arquivoNotFoundException(ArquivoNotFound exception) {
        final ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pb.setTitle("Ops! Não foi possível encontrar o arquivo arquivo");
        pb.setDetail(exception.getMessage());
        return pb;
    }
}
