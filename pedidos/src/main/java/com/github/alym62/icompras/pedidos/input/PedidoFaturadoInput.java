package com.github.alym62.icompras.pedidos.input;

import com.github.alym62.icompras.pedidos.domain.enums.StatusPedido;

public record PedidoFaturadoInput(
        Long codigoDoPedido,
        StatusPedido statusDoPedido,
        String urlNF,
        String codigoDeRastreio
) {
}
