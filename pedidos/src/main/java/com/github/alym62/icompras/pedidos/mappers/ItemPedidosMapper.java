package com.github.alym62.icompras.pedidos.mappers;

import com.github.alym62.icompras.pedidos.controllers.dto.request.ItemPedidoRequestDto;
import com.github.alym62.icompras.pedidos.domain.ItemPedidoPersistence;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemPedidosMapper {
    ItemPedidosMapper INSTANCE = Mappers.getMapper(ItemPedidosMapper.class);

    ItemPedidoPersistence dtoRequestToPersistence(ItemPedidoRequestDto dto);
}
