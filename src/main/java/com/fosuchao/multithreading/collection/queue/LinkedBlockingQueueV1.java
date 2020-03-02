package com.fosuchao.multithreading.collection.queue;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description:
 * @author: Joker Ye
 * @create: 2020/3/2 09:50
 */
public class LinkedBlockingQueueV1 {
    static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        TicketProducer producer = new TicketProducer(queue);
        new Thread(producer).start();

        new Thread(new WatchDog(queue, producer)).start();

        new Thread(new TicketConsumer(queue, producer)).start();
    }
}

class TicketConsumer implements Runnable {
    LinkedBlockingQueue<String> queue;
    TicketProducer producer;

    public TicketConsumer(LinkedBlockingQueue queue, TicketProducer producer) {
        this.queue = queue;
        this.producer = producer;
    }

    public void run() {
        while (!queue.isEmpty() || producer.isRunning()) {
            try {
                String element = queue.take();
                System.out.println("[消费者] 消费：" + element);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[消费者] 消费完毕");
    }
}

class TicketProducer implements Runnable {
    LinkedBlockingQueue<String> queue;
    Boolean runnable;

    public TicketProducer(LinkedBlockingQueue<String> queue) {
        this.queue = queue;
        this.runnable = true;
    }

    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                String ticket = "ticket-" + i;
                queue.put(ticket);
                System.out.println("[生产者] 生产：" + ticket);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[生产者] 生产完毕");
        runnable = false;
    }

    public boolean isRunning() {
        return runnable;
    }
}

class WatchDog implements Runnable {
    LinkedBlockingQueue<String> queue;
    TicketProducer producer;

    public WatchDog(LinkedBlockingQueue queue, TicketProducer producer) {
        this.queue = queue;
        this.producer = producer;
    }

    public void run() {
        while (producer.isRunning()) {
            // 监视队列情况
            System.out.println("[watchDog]: " + queue);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[watchDog] close");
    }
}