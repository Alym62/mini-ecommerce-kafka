package com.github.alym62.icompras.clientes.mapper;

import com.github.alym62.icompras.clientes.controller.dto.request.ClienteRequestDto;
import com.github.alym62.icompras.clientes.controller.dto.response.ClienteResponseDto;
import com.github.alym62.icompras.clientes.domain.ClientePersistence;

public class ClientesMapper {
    private ClientesMapper() {}

    public static ClientePersistence requestDtoToPersistence(ClienteRequestDto dto) {
        ClientePersistence persistence = new ClientePersistence();
        persistence.setNome(dto.nome());
        persistence.setCpf(dto.cpf());
        persistence.setLogradouro(dto.logradouro());
        persistence.setNumero(dto.numero());
        persistence.setBairro(dto.bairro());
        persistence.setEmail(dto.email());
        persistence.setTelefone(dto.telefone());

        return persistence;
    }

    public static ClienteResponseDto persistenceToResponseDto(ClientePersistence persistence) {
        return new ClienteResponseDto(
                persistence.getNome(),
                persistence.getLogradouro(),
                persistence.getNumero(),
                persistence.getBairro(),
                persistence.getEmail(),
                persistence.getTelefone()
        );
    }
}
