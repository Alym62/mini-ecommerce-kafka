package com.github.alym62.icompras.pedidos.publisher;

import com.github.alym62.icompras.pedidos.domain.PedidoPersistence;
import com.github.alym62.icompras.pedidos.mappers.DetalhePedidoMapper;
import com.github.alym62.icompras.pedidos.mappers.proto.PedidoProtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.github.alym62.icompras.pedidos.PedidoProto;

@Component
@RequiredArgsConstructor
@Slf4j
public class PagamentoPusblisher {
    @Value("${icompras.config.kafka.topics.pedidos-pagos}")
    private String topic;

    private final PedidoProtoMapper pedidoProtoMapper;
    private final KafkaTemplate<String, PedidoProto.Pedido> kafkaTemplate;

    public void publicarPedido(PedidoPersistence pedido) {
        log.info("Pedido pago sendo enviado -> {}", pedido.getCodigo());
        PedidoProto.Pedido protoParaMessageDoKafka = pedidoProtoMapper.toProto(pedido);

        // @TODO: Implementar DLQ
        var messageKafka = kafkaTemplate.send(topic, pedido.getCodigo().toString(), protoParaMessageDoKafka);
        messageKafka.whenComplete((resultado, ex) -> {
           if (ex != null) {
               log.error("Erro ao tentar enviar mensagem para o topico -> {}", ex.getMessage());
           } else {
               log.info("Pedido enviado com sucesso");
           }
        });
    }
}
