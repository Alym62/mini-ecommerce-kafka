CREATE TABLE tb_pedidos
(
    codigo          SERIAL    NOT NULL PRIMARY KEY,
    codigo_cliente  BIGINT    NOT NULL,
    data_pedido     TIMESTAMP NOT NULL DEFAULT NOW(),
    chave_pagamento TEXT,
    observacoes     TEXT,
    status          VARCHAR(20) CHECK ( status in ('REALIZADO', 'PAGO', 'FATURADO', 'ENVIADO', 'ERRO_PAGAMENTO',
                                                   'PREPARANDO ENVIO') ),
    total           DECIMAL(16, 2),
    codigo_rastreio VARCHAR(255),
    url_nf          TEXT
);

CREATE TABLE tb_item_pedido
(
    codigo         SERIAL         NOT NULL PRIMARY KEY,
    codigo_pedido  BIGINT         NOT NULL REFERENCES tb_pedidos (codigo),
    codigo_produto BIGINT         NOT NULL,
    quantidade     INT            NOT NULL,
    valor_unitario DECIMAL(16, 2) NOT NULL
);