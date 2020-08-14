package com.galaxy.venus.pc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueModel implements Model {
    private final BlockingQueue<Task> queue;

    private final AtomicInteger increTaskNo = new AtomicInteger(0);

    public BlockingQueueModel(int cap) {
        this.queue = new LinkedBlockingQueue<>(cap);
    }

    @Override
    public Runnable newRunnableConsumer() {
        return new ConsumerImpl();
    }

    @Override
    public Runnable newRunnableProducer() {
        return new ProducerImpl();
    }

    private class ProducerImpl extends AbstractProducer implements Producer, Runnable {

        @Override
        public void produce() throws InterruptedException {
            TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
            Task task = new Task(increTaskNo.getAndIncrement());
            System.out.println("produce :" + task.no);
            queue.put(task);
        }
    }

    private class ConsumerImpl extends AbstractConsumer implements Consumer, Runnable {

        @Override
        public void consume() throws InterruptedException {
            Task task = queue.take();
            TimeUnit.MILLISECONDS.sleep((long) (500 + Math.random() * 500));
            System.out.println("consume " + task.no);
        }
    }

    public static void main(String[] args) {
        Model model = new BlockingQueueModel(20);
        for (int i = 0; i < 2; i++) {
            new Thread(model.newRunnableConsumer()).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(model.newRunnableProducer()).start();
        }
    }
}
