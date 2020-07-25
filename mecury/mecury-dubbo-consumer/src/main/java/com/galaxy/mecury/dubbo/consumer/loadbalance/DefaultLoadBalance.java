package com.galaxy.mecury.dubbo.consumer.loadbalance;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.LoadBalance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Vector;

public class DefaultLoadBalance implements LoadBalance {
    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        System.out.println("[DemoLoadBalance]Select the first invoker...");
        return invokers.get(1);
    }
}
