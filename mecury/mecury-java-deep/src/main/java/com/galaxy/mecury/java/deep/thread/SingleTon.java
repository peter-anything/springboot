package com.galaxy.mecury.java.deep.thread;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class SingleTon {
    private static ReentrantLock lock = new ReentrantLock();
    private volatile static SingleTon singleTon;

    private SingleTon() {
    }

    public static SingleTon getInstance() {
        if (singleTon == null) {
            try {
                lock.lock();
                if (singleTon == null) {
                    singleTon = new SingleTon();
                }
            } finally {
                lock.unlock();
            }
        }

        return singleTon;
    }

    public static void main(String[] args) {
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(20);
        Set<Integer> hashCodeSet = new HashSet<>();

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(20, 40, 3, TimeUnit.SECONDS, queue);

        for (int i = 0; i < 62; i++) {
            threadPool.execute(() -> {
                SingleTon singleTon1 = SingleTon.getInstance();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
//                System.out.println(singleTon1.hashCode());
//                System.out.println(singleTon1.hashCode());
                hashCodeSet.add(singleTon1.hashCode());
            });
        }
        threadPool.shutdown();
        try {//等待直到所有任务完成
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(hashCodeSet);
        for (Integer hashCode : hashCodeSet) {
            System.out.println(hashCode);
        }


//        SingleTon singleTon1 = SingleTon.getInstance();
//        SingleTon singleTon2 = SingleTon.getInstance();
//        SingleTon singleTon3 = SingleTon.getInstance();
//        SingleTon singleTon4 = SingleTon.getInstance();
//        SingleTon singleTon5 = SingleTon.getInstance();
//        System.out.println(singleTon1.hashCode());
//        System.out.println(singleTon2.hashCode());
//        System.out.println(singleTon3.hashCode());
//        System.out.println(singleTon4.hashCode());
//        System.out.println(singleTon5.hashCode());
    }
}
