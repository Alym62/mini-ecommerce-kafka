package com.github.alym62.icompras.pedidos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "stripe", url = "${icompras.pedidos.clients.stripe.url}")
public interface StripeClient {
    @PostMapping("/pay")
    ResponseEntity<String> enviarPagamentoParaStripe(@RequestBody Object pedido);
}
