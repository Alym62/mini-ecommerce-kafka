package com.github.alym62.icompras.pedidos.integrations;

import com.github.alym62.icompras.pedidos.client.ClientesClient;
import com.github.alym62.icompras.pedidos.client.representation.ClienteRepresentation;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClienteIntegration {
    private final ClientesClient clientesClient;

    public ClienteRepresentation obterClienteNoMs(Long codigoCliente) {
        try {
            ResponseEntity<ClienteRepresentation> response = clientesClient.obterDetalhesDoCliente(codigoCliente);
            if (Objects.nonNull(response) && Objects.nonNull(response.getBody())){
                log.info("[MS - Cliente] -> recuperado: {}", response.getBody().nome());
                return response.getBody();
            }

            return null;
        } catch (FeignException.NotFound ex) {
            log.error("[MS - Cliente] -> cliente não recuperado: {} - error: {}", codigoCliente, ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }
}
