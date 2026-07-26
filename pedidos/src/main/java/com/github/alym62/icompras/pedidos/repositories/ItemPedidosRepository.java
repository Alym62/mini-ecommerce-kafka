package com.github.alym62.icompras.pedidos.repositories;

import com.github.alym62.icompras.pedidos.domain.ItemPedidoPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidosRepository extends JpaRepository<ItemPedidoPersistence, Long> {
}
