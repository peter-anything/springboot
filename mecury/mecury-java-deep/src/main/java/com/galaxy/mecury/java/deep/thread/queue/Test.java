package com.galaxy.mecury.java.deep.thread.queue;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Thread> arr = new ArrayList<>(200);
        MyQueue<String> q = new MyQueue<>(100);
        for ( int i = 0; i < 100; i++) {
            Thread t = new Thread(new Producer(q));
            arr.add(t);
        }

        for ( int i = 0; i < 100; i++) {
            Thread t = new Thread(new Consumer(q));
            arr.add(t);
        }

        for (Thread t : arr) {
            t.start();
        }
    }
}
