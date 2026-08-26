package com.github.alym62.icompras.faturamento.bucket;

import org.springframework.http.MediaType;

import java.io.InputStream;

public record BucketFile(
        String nomeDoArquivo,
        InputStream is,
        MediaType type,
        long size
) {
}
