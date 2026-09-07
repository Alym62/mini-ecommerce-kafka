package com.github.alym62.icompras.faturamento.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "minio")
public record MinioConfig(
        String url,
        String accessKey,
        String secretKey,
        String bucket,
        String path
) {
}
