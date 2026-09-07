package com.github.alym62.icompras.logistica.publisher;

import com.github.alym62.icompras.logistica.PedidoEnviadoProto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PedidoEnviadoPublisher {
    @Value("${icompras.config.kafka.topics.pedidos-enviados}")
    private String topico;

    private final KafkaTemplate<String, PedidoEnviadoProto.PedidoEnviado> kafkaTemplate;

    public void enviarPedido(PedidoEnviadoProto.PedidoEnviado pedidoEnviado) {
        log.info("[Logistica] -> Pedido enviado com codigo de rastreio: {}", pedidoEnviado.getCodigoRastreio());

        var messageKafka = kafkaTemplate.send(topico, Long.valueOf(pedidoEnviado.getCodigoPedido()).toString(), pedidoEnviado);
        messageKafka.whenComplete((resultado, ex) -> {
            if (ex != null) {
                log.error("[Logistica] -> Não foi possível publicar o evento no topico {}: {}", topico, ex.getMessage());
            } else {
                log.info("[Logistica] -> Evento enviado ao topico {}: {}", topico, pedidoEnviado.getCodigoRastreio());
            }
        });
    }
}
