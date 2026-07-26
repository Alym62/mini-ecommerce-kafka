package com.github.alym62.icompras.clientes.repository;

import com.github.alym62.icompras.clientes.domain.ClientePersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends JpaRepository<ClientePersistence, Long> {
}
