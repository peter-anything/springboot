package com.galaxy.mecury.java.deep.thread.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyQueue<T> {
    private Object[] elements;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    private int length = 0, addIndex = 0, removeIndex = 0;

    public MyQueue(int size) {
        elements = new Object[size];
    }

    public void add(T object) throws InterruptedException {
        lock.lock();
        try {
            while (length == elements.length) {
                System.out.println("queue is full");
                notFull.await();
            }
            elements[addIndex] = object;
            length++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T remove() throws InterruptedException {
        lock.lock();
        try {
            while (0 == length) {
                System.out.println("queue is empty");
                notEmpty.await();
            }
            Object element = elements[removeIndex];
            length--;
            notFull.signal();
            return (T) element;
        } finally {
            lock.unlock();
        }
    }
}
