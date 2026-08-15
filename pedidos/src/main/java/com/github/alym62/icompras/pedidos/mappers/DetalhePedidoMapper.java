package com.github.alym62.icompras.pedidos.mappers;

import com.github.alym62.icompras.pedidos.domain.ItemPedidoPersistence;
import com.github.alym62.icompras.pedidos.domain.PedidoPersistence;
import com.github.alym62.icompras.pedidos.controllers.dto.response.DetalheItemPedidoResponseDto;
import com.github.alym62.icompras.pedidos.controllers.dto.response.DetalhePedidoResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DetalhePedidoMapper {
    DetalhePedidoMapper INSTANCE = Mappers.getMapper(DetalhePedidoMapper.class);

    @Mapping(source = "codigo", target = "codigoPedido")
    @Mapping(source = "status", target = "statusDoPedido")
    @Mapping(source = "dataPedido", target = "dataPedido", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "detalhesDoPedido.nome", target = "nome")
    @Mapping(source = "detalhesDoPedido.cpf", target = "cpf")
    @Mapping(source = "detalhesDoPedido.logradouro", target = "logradouro")
    @Mapping(source = "detalhesDoPedido.bairro", target = "bairro")
    @Mapping(source = "detalhesDoPedido.email", target = "email")
    @Mapping(source = "detalhesDoPedido.telefone", target = "telefone")
    @Mapping(source = "itens", target = "itens", qualifiedByName = "mapearItensDoPedido")
    DetalhePedidoResponseDto persistenceToRepresentationPub(PedidoPersistence pedido);

    @Named("mapearItensDoPedido")
    default Set<DetalheItemPedidoResponseDto> mapearItensDoPedido(Set<ItemPedidoPersistence> itens) {
        return itens.stream()
                .map(item -> new DetalheItemPedidoResponseDto(
                        item.getCodigoProduto(),
                        item.getDetalheDoItem().nome(),
                        item.getQuantidade(),
                        item.getValorUnitario()
                )).collect(Collectors.toSet());
    }
}
