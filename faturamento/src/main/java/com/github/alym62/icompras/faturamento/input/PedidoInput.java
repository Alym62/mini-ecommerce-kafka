package com.github.alym62.icompras.faturamento.input;

import java.math.BigDecimal;
import java.util.Set;

public record PedidoInput(
        Long codigo,
        ClienteInput cliente,
        String data,
        BigDecimal total,
        Set<ItemPedidoInput> itens
) {
}
