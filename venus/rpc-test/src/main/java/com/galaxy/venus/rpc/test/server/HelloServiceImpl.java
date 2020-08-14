package com.galaxy.venus.rpc.test.server;

import com.galaxy.venus.rpc.server.RpcService;
import com.galaxy.venus.rpc.test.service.HelloService;
import com.galaxy.venus.rpc.test.service.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {
    public HelloServiceImpl(){
        private Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);
    }

    @Override
    public String hello(String name) {
        return "Hello! " + name;
    }

    @Override
    public String hello(Person person) {
        return "Hello! " + person.getFirstName() + " " + person.getLastName();
    }
}
