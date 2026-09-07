package com.github.alym62.icompras.pedidos.subscriber;

import com.github.alym62.icompras.pedidos.FaturamentoPedidoProto;
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
public class PedidoFaturadoSubscriber {
    private final PedidosService pedidosService;
    private final PedidoFaturadoProtoMapper pedidoFaturadoProtoMapper;

    @KafkaListener(
            groupId = "${icompras.config.kafka.group}",
            topics = {"${icompras.config.kafka.topics.pedidos-faturados}",
                    "${icompras.config.kafka.topics.pedidos-enviados}"},
            containerFactory = "concurrentKafkaListenerContainerFactory"
    )
    public void pedidoFaturadoSub(FaturamentoPedidoProto.FaturamentoDoPedido pedidoFaturado) {
        log.info("[Pedidos] -> Pedido faturado recebido: {}", pedidoFaturado.getCodigoPedido());

        PedidoFaturadoInput inputDePedidoFaturado = pedidoFaturadoProtoMapper.toInput(pedidoFaturado, null);
        pedidosService.atualizarPedidoFaturado(
                inputDePedidoFaturado.codigoDoPedido(),
                inputDePedidoFaturado.statusDoPedido(),
                inputDePedidoFaturado.urlNF(),
                inputDePedidoFaturado.codigoDeRastreio()
        );
    }
}
