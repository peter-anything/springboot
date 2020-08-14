package com.galaxy.venus.pc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionModel2 implements Model {
    private final Lock CONSUMER_LOCK = new ReentrantLock();
    private final Condition NOT_EMPTY = CONSUMER_LOCK.newCondition();
    private final Lock PRODUCER_LOCK = new ReentrantLock();
    private final Condition NOT_FULL = PRODUCER_LOCK.newCondition();

    private final Queue<Task> buffer = new LinkedList<>();
    private AtomicInteger bufLen = new AtomicInteger(0);
    private final int cap;

    private AtomicInteger increTaskNo = new AtomicInteger(0);

    public LockConditionModel2(int cap) {
        this.cap = cap;
    }

    @Override
    public Runnable newRunnableConsumer() {
        return new ConsumerImpl();
    }

    @Override
    public Runnable newRunnableProducer() {
        return new ProducerImpl();
    }

    private class ConsumerImpl extends AbstractConsumer implements Consumer, Runnable {

        @Override
        public void consume() throws InterruptedException {
            int newBufSize = -1;

            CONSUMER_LOCK.lockInterruptibly();
            try {
                while (bufLen.get() == 0) {
                    System.out.println("buffer is empty ...");
                    NOT_EMPTY.await();
                }

                Task task = buffer.poll();
                newBufSize = bufLen.decrementAndGet();
                assert task != null;
                // 固定时间范围的消费，模拟相对稳定的服务器处理过程
                Thread.sleep(500 + (long) (Math.random() * 500));
                System.out.println("consume: " + task.no);

                if (newBufSize > 0) {
                    NOT_EMPTY.signalAll();
                }
            } finally {
                CONSUMER_LOCK.unlock();
            }

            if (newBufSize < cap) {
                PRODUCER_LOCK.lockInterruptibly();
                try {
                    NOT_FULL.signalAll();
                } finally {
                    PRODUCER_LOCK.unlock();
                }
            }
        }
    }

    private class ProducerImpl extends AbstractProducer implements Producer, Runnable {

        @Override
        public void produce() throws InterruptedException {
            // 不定期生产，模拟随机的用户请求
            Thread.sleep((long) (Math.random() * 1000));
            int newBufSize = -1;

            PRODUCER_LOCK.lockInterruptibly();
            try {
                while (bufLen.get() == cap) {
                    System.out.println("buffer is full ...");
                    NOT_FULL.await();
                }
                Task task = new Task(increTaskNo.getAndIncrement());
                buffer.offer(task);
                newBufSize = bufLen.incrementAndGet();
                System.out.println("produce: " + task.no);

                if (newBufSize < cap) {
                    NOT_FULL.signalAll();
                }
            } finally {
                PRODUCER_LOCK.unlock();
            }

            if (newBufSize > 0) {
                CONSUMER_LOCK.lockInterruptibly();
                try {
                    NOT_EMPTY.signalAll();
                } finally {
                    CONSUMER_LOCK.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        Model model = new LockConditionModel2(20);
        for (int i = 0; i < 20; i++) {
            new Thread(model.newRunnableConsumer()).start();
        }
        for (int i = 0; i < 20; i++) {
            new Thread(model.newRunnableProducer()).start();
        }
    }
}