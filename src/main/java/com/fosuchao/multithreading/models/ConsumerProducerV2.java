package com.fosuchao.multithreading.models;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @description: 使用ReentrantLock实现生产者消费者模型
 * @author: Joker Ye
 * @create: 2020/3/1 21:56
 */
public class ConsumerProducerV2 {
    private Integer goods = 0;

    private final Integer MAX = 10;

    private ReentrantLock lock = new ReentrantLock();

    private Condition fullCondition = lock.newCondition();      // 产品生产满

    private Condition emptyCondition = lock.newCondition();     // 产品消费空

    public static void main(String[] args) {
        ConsumerProducerV2 cpV2 = new ConsumerProducerV2();
        IntStream.range(0, 20).forEach(i -> new Thread(cpV2::produce).start());
        IntStream.range(0, 10).forEach(i -> new Thread(cpV2::consume).start());
    }

    public void produce() {
        lock.lock();
        try {
            while (goods > MAX) {
                try {
                    fullCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            goods++;
            System.out.println(Thread.currentThread().getName() + "生产产品，当前产品数量：" + goods);
            emptyCondition.signal();    // 通知消费者消费
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        lock.lock();
        try {
            while (goods == 0) {
                try {
                    emptyCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            goods--;
            System.out.println(Thread.currentThread().getName() + "消费产品，当前产品数量：" + goods);
            fullCondition.signal();     // 通知生产者生产
        } finally {
            lock.unlock();
        }
    }
}
