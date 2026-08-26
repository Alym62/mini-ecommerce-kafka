package com.github.alym62.icompras.faturamento;

import com.github.alym62.icompras.faturamento.config.MinioConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(MinioConfig.class)
@SpringBootApplication
public class FaturamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaturamentoApplication.class, args);
	}

}
