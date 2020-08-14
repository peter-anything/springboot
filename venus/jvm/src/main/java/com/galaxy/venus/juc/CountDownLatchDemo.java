package com.galaxy.venus.juc;

import java.sql.Time;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {
    public static void awaitForChildThreads() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        final CountDownLatch latch = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            service.submit(() -> {
                System.out.println("subThread " + Thread.currentThread().getName() + "start ....");
                try {
                    TimeUnit.SECONDS.sleep(1);
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        TimeUnit.SECONDS.sleep(1);
        System.out.println("main thread is watting for sub thread" + Thread.currentThread().getName());
        latch.await();
        service.shutdown();
    }

    public static void startAllThreadsAtOneTime() throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        final CountDownLatch cdOrder = new CountDownLatch(1);
        final CountDownLatch cdAnswer = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            service.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " is waiting for signal... ");
                try {
                    cdOrder.await();
                    System.out.println(Thread.currentThread().getName() + " is running ....");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " has finished assigned job");
                    cdAnswer.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName() + " is going to send signal to all threads");
        cdOrder.countDown();
        System.out.println(Thread.currentThread().getName() + " is waiting for all threads to finish");
        cdAnswer.await();
        System.out.println("All threads finished");
        service.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        startAllThreadsAtOneTime();
    }
}
