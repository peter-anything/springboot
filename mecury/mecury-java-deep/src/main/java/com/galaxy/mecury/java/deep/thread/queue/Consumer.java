package com.galaxy.mecury.java.deep.thread.queue;

public class Consumer implements Runnable {
    private MyQueue<String> queue;
    public Consumer(MyQueue queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(300);
                String obj = queue.remove();
                System.out.println("consume " + obj);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
