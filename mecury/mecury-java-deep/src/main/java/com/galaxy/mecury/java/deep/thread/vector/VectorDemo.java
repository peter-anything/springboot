package com.galaxy.mecury.java.deep.thread.vector;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

public class VectorDemo {
    private static Vector<Integer> vector = new Vector();
    private static AtomicLong value = new AtomicLong();

    public static void addToVector1() {
        for (int i = 0; i < 10; i++) {
            System.out.println(value.incrementAndGet());
        }
    }

    public static void addToVector2() {
        for (int i = 10; i < 20; i++) {
            System.out.println(value.incrementAndGet());
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            addToVector1();
        });

        Thread t2 = new Thread(() -> {
            addToVector2();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(value.get());
    }
}
