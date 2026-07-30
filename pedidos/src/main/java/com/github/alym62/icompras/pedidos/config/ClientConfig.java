package com.github.alym62.icompras.pedidos.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.github.alym62.icompras.pedidos.client")
public class ClientConfig {

}
