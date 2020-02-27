package com.fosuchao.multithreading.executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @description: ThreadPoolExecutor的简单使用
 * @author: Joker Ye
 * @create: 2020/2/27 10:36
 */
public class ThreadPoolExecutorExample {
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            5, 10, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(5));

    public static void main(String[] args) {
        IntStream.range(1, 15).boxed().forEach(i -> {
            executor.execute(new Work());
        });
        // 此时进程不会关闭，需要调用showdown
        executor.shutdown();
    }
}


class Work implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "工作中");
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "工作完成！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}