package com.galaxy.venus.rpc.client.proxy;

import com.galaxy.venus.rpc.client.handler.RpcFuture;

public interface IAsyncObjectProxy {
    RpcFuture call(String funcName, Object... args);
}