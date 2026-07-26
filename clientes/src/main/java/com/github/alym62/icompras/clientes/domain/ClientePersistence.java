package com.github.alym62.icompras.clientes.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientePersistence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name = "logradouro", length = 100)
    private String logradouro;

    @Column(name = "numero", length = 10)
    private String numero;

    @Column(name = "bairro", length = 100)
    private String bairro;

    @Column(name = "email", nullable = false, length = 150, unique = true)
    private String email;

    @Column(name = "telefone", length = 20)
    private String telefone;
}
