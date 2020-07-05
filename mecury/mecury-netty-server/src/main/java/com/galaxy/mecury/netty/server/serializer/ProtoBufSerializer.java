package com.galaxy.mecury.netty.server.serializer;

import java.io.IOException;

public class ProtoBufSerializer implements Serializer {
    @Override
    public byte[] serialize(Object obj) throws IOException {
        return new byte[0];
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) throws IOException {
        return null;
    }
}
