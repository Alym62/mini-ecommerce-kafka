package com.github.alym62.icompras.pedidos.domain;

import com.github.alym62.icompras.pedidos.domain.enums.TipoPagamento;
import lombok.Data;

@Data
public class DadosPagamento {
    private String dadosPagamento;
    private TipoPagamento tipoPagamento;
}
