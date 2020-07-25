package com.galaxy.mecury.dubbo;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

class ReentrantLockTest implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    public void run() {
        for (int j = 0; j < 1000; j++) {
            lock.lock();
            try {
                i++;
                System.out.println(Thread.currentThread().getName() + " " + i);
            } finally {
                lock.unlock();
            }
        }
    }
}

public class DubboProviderTests {

    @Test
    public void testDemo() throws InterruptedException {
        Thread t1 = new Thread(new ReentrantLockTest());
        Thread t2 = new Thread(new ReentrantLockTest());

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
