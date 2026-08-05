package com.github.alym62.icompras.pedidos.repositories;

import com.github.alym62.icompras.pedidos.domain.PedidoPersistence;
import com.github.alym62.icompras.pedidos.domain.enums.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidosRepository extends JpaRepository<PedidoPersistence, Long> {
    boolean existsByCodigoAndChavePagamento(Long codigo, String chavePagamento);

    @Modifying
    @Query(value = "update PedidoPersistence p set p.status = ?1, p.observacoes = ?2 where p.codigo = ?3 and p.chavePagamento = ?4")
    void atualizarStatusDePagamentoPorCodigoAndChaveDePagamento(StatusPedido status, String observacao, Long codigo, String chavePagamento);
}
