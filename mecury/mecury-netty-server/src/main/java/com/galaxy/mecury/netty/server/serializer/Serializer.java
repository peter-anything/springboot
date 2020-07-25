package com.galaxy.mecury.netty.server.serializer;

import java.io.IOException;

public interface Serializer {
    byte[] serialize(Object obj) throws IOException;

    <T> T deserialize(Class<T> clazz, byte[] bytes) throws IOException;
}
