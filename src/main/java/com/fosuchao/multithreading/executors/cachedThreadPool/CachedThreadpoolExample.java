package com.fosuchao.multithreading.executors.cachedThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @description: CachedThreadPool,常用来跑（生命周期短）轻量级的任务
 * @author: Joker Ye
 * @create: 2020/2/27 11:58
 * @noinspection Duplicates
 */
public class CachedThreadpoolExample {
    static ExecutorService executor = Executors.newCachedThreadPool();

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
