package com.github.alym62.icompras.logistica.service;

import com.github.alym62.icompras.logistica.FaturamentoPedidoProto;
import com.github.alym62.icompras.logistica.PedidoEnviadoProto;
import com.github.alym62.icompras.logistica.StatusPedidoProto;
import com.github.alym62.icompras.logistica.publisher.PedidoEnviadoPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class LogisticaService {
    private final PedidoEnviadoPublisher pedidoEnviadoPublisher;

    public void enviarPedido(FaturamentoPedidoProto.FaturamentoDoPedido pedidoFaturado) {
        String codigoDeRastreio = gerarCodigoDeRastreio();
        PedidoEnviadoProto.PedidoEnviado pedidoEnviado = PedidoEnviadoProto.PedidoEnviado.newBuilder()
                .setCodigoPedido(pedidoFaturado.getCodigoPedido())
                .setStatusDoPedido(StatusPedidoProto.StatusPedido.ENVIADO)
                .setCodigoRastreio(codigoDeRastreio)
                .build();

        pedidoEnviadoPublisher.enviarPedido(pedidoEnviado);
    }

    private String gerarCodigoDeRastreio() {
        final Random random = new Random();

        char primeiraLetra = (char)('A' + random.nextInt(26));
        char segundaLetra = (char)('A' + random.nextInt(26));

        int numeros = 100000000 + random.nextInt(900000000);

        return String.format("%s%s%s%s", primeiraLetra, segundaLetra, numeros, "BR");
    }
}
