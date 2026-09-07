package com.github.alym62.icompras.faturamento.config;

import com.github.alym62.icompras.faturamento.PedidoProto;
import com.github.alym62.icompras.faturamento.utils.ProtoBufDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaUrl;

    @Value("${icompras.config.kafka.group}")
    private String groupId;

    @Bean
    public ConsumerFactory<String, PedidoProto.Pedido> consumerFactory() {
        final Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaUrl);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new ProtoBufDeserializer<>(PedidoProto.Pedido.parser())
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PedidoProto.Pedido> concurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PedidoProto.Pedido> listener = new ConcurrentKafkaListenerContainerFactory<>();
        listener.setConsumerFactory(consumerFactory());

        return listener;
    }
}
