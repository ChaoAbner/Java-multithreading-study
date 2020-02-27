package com.fosuchao.multithreading.executors.singelThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @description:
 * @author: Joker Ye
 * @create: 2020/2/27 11:52
 */
public class SingelThreadpoolExample {
    static ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        IntStream.range(1, 10).boxed().forEach(i ->
                executor.submit(
                        () -> {
                            System.out.println(Thread.currentThread().getName() + "工作中");
                            try {
                                Thread.sleep(1000);
                                System.out.println(Thread.currentThread().getName() + "工作完成！");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                ));
        // 手动结束
        executor.shutdown();
    }
}
