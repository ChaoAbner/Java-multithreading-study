package com.fosuchao.multithreading.creator.pool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 使用Executors创建一个线程池
 * 当提交的任务执行完了之后，线程会被重新放回线程池中，等待下一次的任务
 * @Auther: Joker Ye
 * @Date: 2020/2/4 10:15
 */
public class UseExecutors {
    public static void main(String[] args) {
        // 创建一个容量为10的线程池
        ExecutorService executors = Executors.newFixedThreadPool(10);
        // 向线程池中提交一个线程任务
        executors.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        // 提交多个任务
        for (int i = 0; i < 100; i++) {
            executors.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
        // 关闭线程池
        executors.shutdown();
    }
}

