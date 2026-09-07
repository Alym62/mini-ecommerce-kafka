package com.github.alym62.icompras.pedidos.utils;

import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

@Slf4j
@RequiredArgsConstructor
public class ProtoBufDeserializer<T extends Message> implements Deserializer<T> {
    private final Parser<T> parser;

    @Override
    public T deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }

        try {
            return parser.parseFrom(data);
        } catch (Exception exception) {
            log.error("Não foi possível serializar a mensagem em Protobuf: {}", exception.getMessage());
            return null;
        }
    }
}
