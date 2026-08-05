package com.github.alym62.icompras.pedidos.integrations;

import com.github.alym62.icompras.pedidos.client.ProdutosClient;
import com.github.alym62.icompras.pedidos.client.representation.ProdutoRepresentation;
import com.github.alym62.icompras.pedidos.exceptions.ValidationException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProdutoIntegration {
    private final ProdutosClient produtosClient;

    public ProdutoRepresentation obterProdutoNoMs(Long codigoDoProduto) {
        try {
            ResponseEntity<ProdutoRepresentation> response = produtosClient.obterDetalhesDoProduto(codigoDoProduto);
            if (Objects.nonNull(response) && Objects.nonNull(response.getBody())){
                log.info("[MS - Produto] -> recuperado: {}", codigoDoProduto);
                return response.getBody();
            }

            return null;
        } catch (FeignException.NotFound ex) {
            log.error("[MS - Produto] -> Produto não encontrado: {} - error: {}", codigoDoProduto, ex.getMessage());
            throw new ValidationException(ex.getMessage(), "codigoProduto");
        }
    }
}
