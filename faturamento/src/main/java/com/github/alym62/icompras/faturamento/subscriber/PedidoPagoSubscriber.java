package com.github.alym62.icompras.faturamento.subscriber;

import com.github.alym62.icompras.faturamento.PedidoProto;
import com.github.alym62.icompras.faturamento.input.PedidoInput;
import com.github.alym62.icompras.faturamento.mapper.PedidoProtoMapper;
import com.github.alym62.icompras.faturamento.service.NotaFiscalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PedidoPagoSubscriber {
    private final PedidoProtoMapper mapper;
    private final NotaFiscalService notaFiscalService;

    @KafkaListener(
            groupId = "${icompras.config.kafka.group}",
            topics = "${icompras.config.kafka.topics.pedidos-pagos}",
            containerFactory = "concurrentKafkaListenerContainerFactory"
    )
    public void pedidoSub(PedidoProto.Pedido pedido) {
        try {
            if (pedido == null) {
                return;
            }

            log.info("Pedido consumido para faturamento: {}", pedido.getCodigoPedido());

            PedidoInput pedidoInput = mapper.protoToPedidoInput(pedido);
            notaFiscalService.gerarNotaFiscal(pedidoInput);
        } catch (Exception exception) {
            log.error("Não foi possível consumir a mensagem do topico: {}", exception.getMessage());
        }
    }
}
