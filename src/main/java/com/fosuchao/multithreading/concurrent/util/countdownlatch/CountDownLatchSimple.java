package com.fosuchao.multithreading.concurrent.util.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: 使用countdownlatch模拟一个爬虫应用（类似join的功能）
 * @Auther: Joker Ye
 * @Date: 2020/2/6 10:46
 */
public class CountDownLatchSimple {
    private static CountDownLatch latch = new CountDownLatch(5);

    public static void main(String[] args) throws InterruptedException {
        // 主任务，开启爬虫线程
        for (int i = 0; i < 5; i++) {
            String bound = i * 100 + "-" + (i + 1) * 100;
            new Thread(new Spider("爬取数据范围：" + bound, latch)).start();
        }

        // 等待所有爬虫线程完成
        latch.await();
        System.out.println("all work done!");
        System.out.println("进行数据分析");
    }
}

class Spider implements Runnable {
    private String work;
    private CountDownLatch latch;
    final Random random = new Random(System.currentTimeMillis());

    public Spider(String work, CountDownLatch latch) {
        this.work = work;
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "正在执行爬虫任务");
        try {
            Thread.sleep(random.nextInt(3000));
            System.out.println(Thread.currentThread().getName() + "任务完成");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
