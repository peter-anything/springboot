package com.galaxy.mecury.netty.server.protocol;

import lombok.Data;

@Data
public class RpcResponse {
    private String requestId;
    private String error;
    private Object result;
}
