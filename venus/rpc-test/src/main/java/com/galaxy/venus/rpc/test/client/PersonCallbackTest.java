package com.galaxy.venus.rpc.test.client;

import com.galaxy.venus.rpc.client.RpcClient;
import com.galaxy.venus.rpc.client.discovery.ServiceDiscovery;
import com.galaxy.venus.rpc.client.handler.AsyncRPCCallback;
import com.galaxy.venus.rpc.client.handler.RpcFuture;
import com.galaxy.venus.rpc.client.proxy.IAsyncObjectProxy;
import com.galaxy.venus.rpc.test.service.Person;
import com.galaxy.venus.rpc.test.service.PersonService;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class PersonCallbackTest {
    public static void main(String[] args) {
        ServiceDiscovery serviceDiscovery = new ServiceDiscovery("10.217.59.164:2181");
        final RpcClient rpcClient = new RpcClient(serviceDiscovery);
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        try {
            IAsyncObjectProxy client = rpcClient.createAsync(PersonService.class);
            int num = 5;
            RpcFuture helloPersonFuture = client.call("GetTestPerson", "xiaoming", num);
            helloPersonFuture.addCallback(new AsyncRPCCallback() {
                @Override
                public void success(Object result) {
                    List<Person> persons = (List<Person>) result;
                    for (int i = 0; i < persons.size(); ++i) {
                        System.out.println(persons.get(i));
                    }
                    countDownLatch.countDown();
                }

                @Override
                public void fail(Exception e) {
                    System.out.println(e);
                    countDownLatch.countDown();
                }
            });

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        rpcClient.stop();

        System.out.println("End");
    }
}
