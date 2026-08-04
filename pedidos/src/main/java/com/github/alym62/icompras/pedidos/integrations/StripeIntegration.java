package com.github.alym62.icompras.pedidos.integrations;

import com.github.alym62.icompras.pedidos.client.StripeClient;
import com.github.alym62.icompras.pedidos.client.representation.ProdutoRepresentation;
import com.github.alym62.icompras.pedidos.client.representation.StripeRepresentation;
import com.github.alym62.icompras.pedidos.domain.PedidoPersistence;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class StripeIntegration {
    private final StripeClient stripeClient;

    public String enviarPagamento(PedidoPersistence pedidoPersistence) {
        try {
            ResponseEntity<StripeRepresentation> response = stripeClient.enviarPagamentoParaStripe();
            if (Objects.nonNull(response) && Objects.nonNull(response.getBody())){
                log.info("[Pagamento - Stripe] -> pedido: {}", pedidoPersistence);
                return response.getBody().chaveDePagamento();
            }

            return null;
        } catch (FeignException.BadGateway ex) {
            log.error("[Pagamento - Stripe] -> Não foi possível realizar o pagamento do pedido: {}", ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }
}
