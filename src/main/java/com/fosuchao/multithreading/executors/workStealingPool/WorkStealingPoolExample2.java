package com.fosuchao.multithreading.executors.workStealingPool;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @description: WorkStealingPool使用callable
 * @author: Joker Ye
 * @create: 2020/2/27 14:09
 */
public class WorkStealingPoolExample2 {
    static ExecutorService executor = Executors.newWorkStealingPool();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Callable<String>> callableList = IntStream.range(0, 20).boxed().map(i ->
                (Callable<String>) () -> {
                    System.out.println(Thread.currentThread().getName() + "working..");
                    sleep(2);
                    System.out.println(Thread.currentThread().getName() + "finish!!");
                    return "Task-" + i;
                }).collect(toList());


        List<Future<String>> futures = executor.invokeAll(callableList);
        for (Future<String> future : futures) {
            // future.get()方法会阻塞，直到结果返回
            System.out.println(future.get());
        }

    }

    private static void sleep(int time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
