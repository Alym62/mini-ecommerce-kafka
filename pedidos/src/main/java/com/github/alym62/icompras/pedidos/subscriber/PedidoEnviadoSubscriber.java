package com.github.alym62.icompras.pedidos.subscriber;

import com.github.alym62.icompras.pedidos.PedidoEnviadoProto;
import com.github.alym62.icompras.pedidos.input.PedidoFaturadoInput;
import com.github.alym62.icompras.pedidos.mappers.proto.PedidoFaturadoProtoMapper;
import com.github.alym62.icompras.pedidos.services.PedidosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PedidoEnviadoSubscriber {
    private final PedidosService pedidosService;
    private final PedidoFaturadoProtoMapper pedidoFaturadoProtoMapper;

    @KafkaListener(
            groupId = "${icompras.config.kafka.group}",
            topics = "${icompras.config.kafka.topics.pedidos-enviados}",
            containerFactory = "concurrentKafkaListenerContainerFactoryPedidoEnviado"
    )
    public void pedidoEnviadoSub(PedidoEnviadoProto.PedidoEnviado pedidoEnviado) {
        log.info("[Pedidos] -> Pedido enviado recebido: {}", pedidoEnviado.getCodigoPedido());

        PedidoFaturadoInput inputDePedidoFaturado = pedidoFaturadoProtoMapper.toInput(pedidoEnviado);
        pedidosService.atualizarPedidoFaturado(
                inputDePedidoFaturado.codigoDoPedido(),
                inputDePedidoFaturado.statusDoPedido(),
                inputDePedidoFaturado.urlNF(),
                inputDePedidoFaturado.codigoDeRastreio()
        );
    }
}
