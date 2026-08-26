package com.github.alym62.icompras.faturamento.service;

import com.github.alym62.icompras.faturamento.bucket.BucketFile;
import com.github.alym62.icompras.faturamento.config.MinioConfig;
import com.github.alym62.icompras.faturamento.exceptions.UploadDeArquivoException;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class BucketService {
    private final MinioClient minioClient;
    private final MinioConfig minioConfig;

    public void uploadDoArquivoDeFaturamento(MultipartFile arquivo) {
        try (InputStream is = arquivo.getInputStream()) {
            BucketFile arquivoParaBucket = criarArquivoBucketFile(arquivo, is);
            String nomeDoArquivo = criarNomeDoArquivoParaUploadPadronizado(arquivoParaBucket.nomeDoArquivo());

            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    .bucket(minioConfig.bucket())
                    .object(nomeDoArquivo)
                    .stream(arquivoParaBucket.is(), arquivoParaBucket.size(), -1)
                    .contentType(arquivoParaBucket.type().toString())
                    .build();

            minioClient.putObject(objectArgs);
        } catch (Exception exception) {
            log.error("[Bucket] -> Erro ao tentar realizar upload do arquivo - {}", exception.getMessage());
            throw new UploadDeArquivoException(exception.getMessage());
        }
    }

    public String obterArquivoDeFaturamento(String nomeDoArquivo) {
        try {
            GetPresignedObjectUrlArgs objectArgs = GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(minioConfig.bucket())
                    .object(nomeDoArquivo)
                    .expiry(1, TimeUnit.HOURS)
                    .build();

            return minioClient.getPresignedObjectUrl(objectArgs);
        } catch (Exception exception) {
            log.error("[Bucket] -> Erro ao tentar realizar recuperar o arquivo - {}", nomeDoArquivo);
            throw new RuntimeException();
        }
    }

    private BucketFile criarArquivoBucketFile(MultipartFile arquivo, InputStream is) {
        if (Objects.isNull(arquivo.getContentType())) {
            throw new UploadDeArquivoException("É necessário enviar o content-type do arquivo");
        }

        return new BucketFile(
                arquivo.getOriginalFilename(),
                is,
                MediaType.parseMediaType(arquivo.getContentType()),
                arquivo.getSize()
        );
    }

    private String criarNomeDoArquivoParaUploadPadronizado(String nomeDoArquivo) {
        LocalDateTime hoje = LocalDateTime.now();
        return String.format("%s/Id-pedido-%s-%s", minioConfig.path(), nomeDoArquivo,
                hoje.format(DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss")));
    }
}
