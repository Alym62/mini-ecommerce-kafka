CREATE TABLE tb_clientes
(
    codigo     SERIAL       NOT NULL PRIMARY KEY,
    nome       VARCHAR(150) NOT NULL,
    cpf        VARCHAR(11)  NOT NULL,
    logradouro VARCHAR(100),
    numero     VARCHAR(10),
    bairro     VARCHAR(100),
    email      VARCHAR(150),
    telefone   VARCHAR(20)
);