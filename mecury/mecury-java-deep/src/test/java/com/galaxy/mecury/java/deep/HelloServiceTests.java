package com.galaxy.mecury.java.deep;

import com.galaxy.mecury.java.deep.service.HelloService;
import org.junit.Test;

import java.util.ServiceLoader;

public class HelloServiceTests {
    @Test
    public void testServiceLoader() {
        ServiceLoader<HelloService> helloServiceServiceLoader = ServiceLoader.load(HelloService.class);
        for (HelloService helloService : helloServiceServiceLoader) {
            helloService.sayHello();
        }
    }
}
