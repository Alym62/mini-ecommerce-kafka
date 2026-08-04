package com.github.alym62.icompras.pedidos.client;

import com.github.alym62.icompras.pedidos.client.representation.StripeRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "stripe", url = "${icompras.pedidos.clients.stripe.url}")
public interface StripeClient {
    @PostMapping("/pay")
    ResponseEntity<StripeRepresentation> enviarPagamentoParaStripe();
}
