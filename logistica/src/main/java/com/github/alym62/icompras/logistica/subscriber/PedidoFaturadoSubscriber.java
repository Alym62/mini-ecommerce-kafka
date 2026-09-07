package com.github.alym62.icompras.logistica.subscriber;

import com.github.alym62.icompras.logistica.FaturamentoPedidoProto;
import com.github.alym62.icompras.logistica.service.LogisticaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PedidoFaturadoSubscriber {
    private final LogisticaService logisticaService;

    @KafkaListener(
            groupId = "${icompras.config.kafka.group}",
            topics = "${icompras.config.kafka.topics.pedidos-faturados}",
            containerFactory = "concurrentKafkaListenerContainerFactory"
    )
    public void pedidoFaturadoSub(FaturamentoPedidoProto.FaturamentoDoPedido pedidoFaturado) {
        log.info("[Logistica] -> Pedido faturado recebido: {}", pedidoFaturado.getCodigoPedido());

        logisticaService.enviarPedido(pedidoFaturado);
    }
}
