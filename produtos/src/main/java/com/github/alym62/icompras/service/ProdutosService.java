package com.github.alym62.icompras.service;

import com.github.alym62.icompras.domain.ProdutoPersistence;
import com.github.alym62.icompras.repository.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutosService {
    private final ProdutosRepository produtosRepository;

    public ProdutoPersistence salvarProduto(ProdutoPersistence persistence) {
        return produtosRepository.save(persistence);
    }

    public Optional<ProdutoPersistence> obterProdutoPorCodigo(Long codigo) {
        return produtosRepository.findById(codigo);
    }
}
