package com.github.alym62.icompras.pedidos.domain;

import com.github.alym62.icompras.pedidos.domain.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_pedidos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoPersistence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "codigo_cliente", nullable = false)
    private Long codigoCliente;

    @Column(name = "data_pedido", nullable = false)
    private LocalDateTime dataPedido;

    @Column(name = "total", precision = 16, scale = 2)
    private BigDecimal total;

    @Column(name = "codigo_rastreio")
    private String codigoRastreio;

    @Column(name = "chave_pagamento")
    private String chavePagamento;

    @Column(name = "observacoes")
    private String observacoes;

    @Column(name = "url_nf")
    private String urlNotaFiscal;

    @Column(name = "status", length = 20)
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ItemPedidoPersistence> itens;

    @Transient
    private DadosPagamento dadosDePagamento;
}
