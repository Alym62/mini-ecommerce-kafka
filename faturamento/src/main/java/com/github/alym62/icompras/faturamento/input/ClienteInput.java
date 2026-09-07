package com.github.alym62.icompras.faturamento.input;

public record ClienteInput(
        String nome,
        String cpf,
        String logradouro,
        String numero,
        String bairro,
        String email,
        String telefone
) {
}
