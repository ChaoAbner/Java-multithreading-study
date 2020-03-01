package com.fosuchao.multithreading.models;

import java.util.stream.IntStream;

/**
 * @description: 使用wait和notify实现生产者消费者模型
 * @author: Joker Ye
 * @create: 2020/3/1 21:22
 */
public class ConsumerProducerV1 {
    private static Integer goods = 0;

    private static Object lock = new Object();

    public static void main(String[] args) {
        IntStream.range(0, 5).forEach(i -> new Thread(new Producer(), "thread-"+i).start());
        IntStream.range(0, 5).forEach(i -> new Thread(new Consumer(), "thread-"+i).start());
    }

    static class Producer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock) {
                    while (goods >= 10) {
                        try {
                            System.out.println("产品生产过剩，等待消费。。。");
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + "生产产品，当前数量：" + ++goods);
                    lock.notifyAll();
                }
            }
        }
    }

    static class Consumer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock) {
                    while (goods <= 0) {
                        try {
                            System.out.println("产品消费完了，等待生产。。。");
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + "消费产品，当前数量：" + --goods);
                    lock.notifyAll();
                }
            }
        }
    }
}
