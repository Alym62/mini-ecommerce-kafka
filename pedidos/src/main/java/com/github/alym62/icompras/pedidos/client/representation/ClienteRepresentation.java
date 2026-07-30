package com.github.alym62.icompras.pedidos.client.representation;

public record ClienteRepresentation(
        Long codigo,
        String nome,
        String logradouro,
        String numero,
        String bairro,
        String email,
        String telefone
) {
}
