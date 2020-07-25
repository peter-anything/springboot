package com.galaxy.mecury.java.deep.thread.queue;

public class Producer implements Runnable {
    private MyQueue<String> queue;

    public Producer(MyQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(300);
                queue.add("hello ");
                System.out.println("produce " + "hello ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
