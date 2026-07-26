package com.github.alym62.icompras.pedidos.mappers;

import com.github.alym62.icompras.pedidos.controllers.dto.request.ItemPedidoRequestDto;
import com.github.alym62.icompras.pedidos.controllers.dto.request.NovoPedidoRequestDto;
import com.github.alym62.icompras.pedidos.domain.ItemPedidoPersistence;
import com.github.alym62.icompras.pedidos.domain.PedidoPersistence;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PedidosMapper {
    PedidosMapper INSTANCE = Mappers.getMapper(PedidosMapper.class);
    ItemPedidosMapper ITEM_PEDIDOS_MAPPER_INSTANCE = ItemPedidosMapper.INSTANCE;

    @Mapping(source = "itens", target = "itens", qualifiedByName = "mapearItensDoPedido")
    PedidoPersistence dtoRequestToPersistence(NovoPedidoRequestDto dto);

    @Named("mapearItensDoPedido")
    default Set<ItemPedidoPersistence> mapearItensDoPedido(Set<ItemPedidoRequestDto> dtos) {
        return dtos.stream()
                .map(ITEM_PEDIDOS_MAPPER_INSTANCE::dtoRequestToPersistence)
                .collect(Collectors.toSet());
    }
}
