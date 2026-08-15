package com.github.alym62.icompras.pedidos.mappers;

import com.github.alym62.icompras.pedidos.domain.PedidoPersistence;
import com.github.alym62.icompras.pedidos.publisher.representation.DetalhePedidoRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

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
    DetalhePedidoRepresentation persistenceToRepresentationPub(PedidoPersistence pedido);
}
