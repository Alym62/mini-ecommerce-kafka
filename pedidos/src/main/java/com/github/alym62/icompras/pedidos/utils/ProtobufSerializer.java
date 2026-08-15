package com.github.alym62.icompras.pedidos.utils;

import com.google.protobuf.Message;
import org.apache.kafka.common.serialization.Serializer;

public class ProtobufSerializer <T extends Message> implements Serializer<T> {
    @Override
    public byte[] serialize(String topic, T data) {
        if (data == null) {
            return null;
        }

        return data.toByteArray();
    }
}
