package com.github.alym62.icompras.faturamento.publisher;

import com.github.alym62.icompras.faturamento.FaturamentoPedidoProto;
import com.github.alym62.icompras.faturamento.mapper.FaturamentoPedidoProtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FaturamentoPublisher {
    @Value("${icompras.config.kafka.topics.pedidos-faturados}")
    private String topico;

    private final KafkaTemplate<String, FaturamentoPedidoProto.FaturamentoDoPedido> kafkaTemplate;
    private final FaturamentoPedidoProtoMapper faturamentoMapper;

    public void publicarPedidoFaturado(Long codigoPedido, String urlNF) {
        FaturamentoPedidoProto.FaturamentoDoPedido pedidoFaturado = faturamentoMapper.pedidoInputToProto(codigoPedido, urlNF);

        // @TODO: Implementar DLQ
        var messageKafka = kafkaTemplate.send(topico, codigoPedido.toString(), pedidoFaturado);
        messageKafka.whenComplete((resultado, ex) -> {
            if (ex != null) {
                log.error("[Faturamento] -> Não foi possível encaminhar a mensagem para o topico {}: {}", topico, ex.getMessage());
            } else {
                log.info("[Faturamento] -> Evento publicado com sucesso no topico {}", topico);
            }
        });
    }
}
