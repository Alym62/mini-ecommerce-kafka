package com.github.alym62.icompras.pedidos.controllers.dto.request;

import java.util.Set;

public record NovoPedidoRequestDto(
        Long codigoCliente,
        Set<ItemPedidoRequestDto> itens,
        DadosPagamentoDto dadosDePagamento
) {
}
