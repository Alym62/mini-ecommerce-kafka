CREATE TABLE tb_produtos
(
    codigo         BIGSERIAL         NOT NULL PRIMARY KEY,
    nome           VARCHAR(100)   NOT NULL,
    valor_unitario DECIMAL(16, 2) NOT NULL
);