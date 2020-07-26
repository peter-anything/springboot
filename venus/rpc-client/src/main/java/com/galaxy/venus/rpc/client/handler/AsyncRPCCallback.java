package com.galaxy.venus.rpc.client.handler;

public interface AsyncRPCCallback {
    void success(Object result);
    void fail(Exception e);
}
