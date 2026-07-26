package com.github.alym62.icompras.mapper;

import com.github.alym62.icompras.controller.dto.request.ProdutoRequestDto;
import com.github.alym62.icompras.controller.dto.response.ProdutoResponseDto;
import com.github.alym62.icompras.domain.ProdutoPersistence;

public final class ProdutosMapper {
    private ProdutosMapper() {}

    public static ProdutoResponseDto persistenceToResponseDto(ProdutoPersistence persistence) {
        return new ProdutoResponseDto(
                persistence.getNome(),
                persistence.getValorUnitario()
        );
    }

    public static ProdutoPersistence requestDtoToPersistence(ProdutoRequestDto dto) {
        ProdutoPersistence persistence = new ProdutoPersistence();
        persistence.setNome(dto.nome());
        persistence.setValorUnitario(dto.valorUnitario());

        return persistence;
    }
}
