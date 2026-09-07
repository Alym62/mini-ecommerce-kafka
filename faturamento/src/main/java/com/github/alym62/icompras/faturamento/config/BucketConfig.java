package com.github.alym62.icompras.faturamento.config;

import com.github.alym62.icompras.faturamento.config.props.MinioConfig;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BucketConfig {
    private final MinioConfig minioConfig;

    @Bean
    public MinioClient bucketClientMinio() {
        return MinioClient.builder()
                .endpoint(minioConfig.url())
                .credentials(minioConfig.accessKey(), minioConfig.secretKey())
                .build();
    }
}
