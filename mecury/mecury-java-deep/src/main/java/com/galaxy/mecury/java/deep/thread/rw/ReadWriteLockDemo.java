package com.galaxy.mecury.java.deep.thread.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static volatile boolean isUpdated;
    private static Map<String, Object> map = new HashMap<>();
    private static Lock readLock = lock.readLock();
    private static Lock writeLock = lock.writeLock();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                readWriteFile(Thread.currentThread(), "name1");

            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                readWriteFile(Thread.currentThread(), "name2");
            }
        });

        Thread.sleep(2000);
        isUpdated = false;
    }

    public static void readWriteFile(Thread thread, String name) {
        System.out.println(Thread.currentThread().getName());
        readLock.lock();
        if (!isUpdated) {
            readLock.unlock();
            writeLock.lock();

            try {
                if (!isUpdated) {
                    isUpdated = true;
                    map.put("name", name);
                }
                readLock.lock();
            } finally {
                writeLock.unlock();
            }

        }

        Object obj = map.get("name");
        System.out.println(obj);
        System.out.println(Thread.currentThread().getName());
        readLock.unlock();
    }

    public static void readFile(Thread thread) {
        lock.readLock().lock();
        boolean readLock = lock.isWriteLocked();
        if (!readLock) {
            System.out.println("current thread lock is write lock");
        }

        try {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + " is reading ...");
            }
            System.out.println(thread.getName() + " read operation is finished");
        } finally {
            System.out.println("release read lock");
            lock.readLock().unlock();
        }
    }

    public static void writeFile(Thread thread) {
        lock.writeLock().lock();
        boolean writeLock = lock.isWriteLocked();
        if (writeLock) {
            System.out.println("current is write lock");
        }
        try {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + " is writing ...");
            }
            System.out.println(thread.getName() + " write operation is finished");
        } finally {
            System.out.println("release write lock");
            lock.writeLock().unlock();
        }
    }
}
