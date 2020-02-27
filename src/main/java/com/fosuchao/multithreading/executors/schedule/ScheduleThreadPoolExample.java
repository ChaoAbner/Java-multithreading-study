package com.fosuchao.multithreading.executors.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description: 使用ScheduleThreadPool实现定时任务
 * 如果任务的执行时间大于了定时时间，那么任务将会被推迟。
 * @author: Joker Ye
 * @create: 2020/2/27 14:46
 */
public class ScheduleThreadPoolExample {
    static ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);

    public static void main(String[] args) {
//        for (int i = 0; i < 5; i++) {
            executor.scheduleAtFixedRate(new TimerTask(), 1L, 1, TimeUnit.SECONDS);
//        }
    }
}

class TimerTask implements Runnable {
    String name = Thread.currentThread().getName();
    @Override
    public void run() {
        try {
            System.out.println(name + "task working...");
            TimeUnit.SECONDS.sleep(3);
            System.out.println(name + "task working done!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}