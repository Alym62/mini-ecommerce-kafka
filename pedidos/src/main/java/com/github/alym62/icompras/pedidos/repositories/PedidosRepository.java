package com.github.alym62.icompras.pedidos.repositories;

import com.github.alym62.icompras.pedidos.domain.PedidoPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidosRepository extends JpaRepository<PedidoPersistence, Long> {
}
