package com.fosuchao.multithreading.util.cyclibarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Description: 各个线程相互等待，直到count=0
 * @Auther: Joker Ye
 * @Date: 2020/2/6 10:47
 */
public class CyclicBarrierTest {
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
        @Override
        public void run() {
            System.out.println("all task done!");
        }
    });

    public static void main(String[] args) {

        IntStream.of(1, 2, 3)
                .forEach(item -> {
                    new Thread(new Worker(cyclicBarrier),  "Thread-" + item).start();
                });
    }
}

class Worker implements Runnable {
    CyclicBarrier cyclicBarrier;

    public Worker(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "执行任务中。。。");
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName() + "任务执行完毕！");
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
