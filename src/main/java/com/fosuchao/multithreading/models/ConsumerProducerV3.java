package com.fosuchao.multithreading.models;

import javax.xml.transform.sax.SAXTransformerFactory;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

/**
 * @description: 使用阻塞队列BlockingQueue实现
 * @author: Joker Ye
 * @create: 2020/3/1 22:06
 */
public class ConsumerProducerV3 {

    private static LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        IntStream.range(0, 1).forEach(i -> new Thread(new Producer(blockingQueue)).start());
        IntStream.range(0, 2).forEach(i -> new Thread(new Consumer(blockingQueue)).start());
    }

    static class Producer implements Runnable {
        LinkedBlockingQueue<Integer> blockingQueue;

        public Producer(LinkedBlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    blockingQueue.put(i);
                    System.out.println(Thread.currentThread().getName() + "生产产品，当前数量：" + blockingQueue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        LinkedBlockingQueue<Integer> blockingQueue;

        public Consumer(LinkedBlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    blockingQueue.take();
                    System.out.println(Thread.currentThread().getName() + "消费产品，当前数量：" + blockingQueue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
