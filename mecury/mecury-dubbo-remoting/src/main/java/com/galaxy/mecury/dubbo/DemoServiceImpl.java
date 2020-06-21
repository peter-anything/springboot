package com.galaxy.mecury.dubbo;

public class DemoServiceImpl implements DemoService {
    public void sayHello(String name) {
        System.out.println("hello " + name);
    }

    public int plus(int a, int b) {
        return a + b;
    }
}