package com.github.alym62.icompras.pedidos.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tb_item_pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoPersistence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "codigo_produto")
    private Long codigoProduto;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "valor_unitario", precision = 16, scale = 2)
    private BigDecimal valorUnitario;

    @JoinColumn(name = "codigo_pedido")
    @ManyToOne(fetch = FetchType.LAZY)
    private PedidoPersistence pedido;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedidoPersistence that = (ItemPedidoPersistence) o;
        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
