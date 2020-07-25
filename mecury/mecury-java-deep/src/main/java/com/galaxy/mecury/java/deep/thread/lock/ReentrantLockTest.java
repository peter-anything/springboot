package com.galaxy.mecury.java.deep.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

class L {
    public static int sharedV = 0;

    public L() {
    }

    public static synchronized void add() {
        sharedV++;
    }
}

public class ReentrantLockTest {

    private static int sharedV = 0;
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        L.add();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        L.add();
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                    }
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(L.sharedV);
        System.out.println("Hello");
    }
}
