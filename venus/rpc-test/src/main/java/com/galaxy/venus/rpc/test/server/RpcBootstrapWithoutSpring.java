package com.galaxy.venus.rpc.test.server;

import com.galaxy.venus.rpc.server.RpcServer;
import com.galaxy.venus.rpc.server.ServiceRegistry;
import com.galaxy.venus.rpc.test.service.HelloService;
import com.galaxy.venus.rpc.test.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RpcBootstrapWithoutSpring {
    private static final Logger logger = LoggerFactory.getLogger(RpcBootstrapWithoutSpring.class);

    public static void main(String[] args) {
        String serverAddress = "127.0.0.1:18866";
        ServiceRegistry serviceRegistry = new ServiceRegistry("10.217.59.164:2181");
        RpcServer rpcServer = new RpcServer(serverAddress, serviceRegistry);
        HelloService helloService = new HelloServiceImpl();
        PersonService personService = new PersonServiceImpl();
        rpcServer.addService(HelloService.class.getName(), helloService);
        rpcServer.addService(PersonService.class.getName(), personService);
        try {
            rpcServer.start();
        } catch (Exception ex) {
            logger.error("Exception: {}", ex);
        }
    }
}
