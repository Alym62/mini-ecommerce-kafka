package com.github.alym62.icompras.clientes.controller.dto.request;

public record ClienteRequestDto(
        String nome,
        String cpf,
        String logradouro,
        String numero,
        String bairro,
        String email,
        String telefone
) {
}
