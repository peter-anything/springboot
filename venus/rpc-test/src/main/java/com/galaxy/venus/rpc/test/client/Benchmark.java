package com.galaxy.venus.rpc.test.client;

import com.galaxy.venus.rpc.client.RpcClient;
import com.galaxy.venus.rpc.client.discovery.ServiceDiscovery;
import com.galaxy.venus.rpc.test.service.HelloService;

public class Benchmark {
    public static void main(String[] args) throws InterruptedException {
        ServiceDiscovery serviceDiscovery = new ServiceDiscovery("10.217.59.164:2181");
        final RpcClient rpcClient = new RpcClient(serviceDiscovery);

        int threadNum = 1;
        final int requestNum = 10;
        Thread[] threads = new Thread[threadNum];

        long startTime = System.currentTimeMillis();
        //benchmark for sync call
        for (int i = 0; i < threadNum; ++i) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < requestNum; i++) {
                        final HelloService syncClient = rpcClient.create(HelloService.class);
                        String result = syncClient.hello(Integer.toString(i));
                        if (!result.equals("Hello! " + i)) {
                            System.out.println("error = " + result);
                        } else {
                            System.out.println("result = " + result);
                        }
                    }
                }
            });
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        long timeCost = (System.currentTimeMillis() - startTime);
        String msg = String.format("Sync call total-time-cost:%sms, req/s=%s", timeCost, ((double) (requestNum * threadNum)) / timeCost * 1000);
        System.out.println(msg);

        rpcClient.stop();
    }
}
