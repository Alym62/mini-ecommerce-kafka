package com.github.alym62.icompras.clientes.controller.dto.response;

public record ClienteResponseDto(
        String nome,
        String logradouro,
        String numero,
        String bairro,
        String email,
        String telefone
) {
}
