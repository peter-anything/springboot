package com.galaxy.mecury.netty.server.protocol;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RpcRequest {
    private String requestId;
    private String className;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] parameters;
}
