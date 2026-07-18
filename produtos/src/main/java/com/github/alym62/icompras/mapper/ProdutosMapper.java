package com.github.alym62.icompras.mapper;

import com.github.alym62.icompras.controller.dto.request.ProdutoRequestDto;
import com.github.alym62.icompras.controller.dto.response.ProdutoResponseDto;
import com.github.alym62.icompras.domain.ProdutoPersistence;

public final class ProdutosMapper {
    private ProdutosMapper() {}

    public static ProdutoResponseDto persistenceToResponseDto(ProdutoPersistence persistence) {
        return new ProdutoResponseDto();
    }

    public static ProdutoPersistence requestDtoToPersistence(ProdutoRequestDto dto) {
        return new ProdutoPersistence();
    }
}
