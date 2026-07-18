package com.github.alym62.icompras.repository;

import com.github.alym62.icompras.domain.ProdutoPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends JpaRepository<ProdutoPersistence, Long> {
}
