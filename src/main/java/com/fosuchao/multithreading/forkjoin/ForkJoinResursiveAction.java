package com.fosuchao.multithreading.forkjoin;

import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @description: RecursiveAction 计算没有返回值
 * @author: Joker Ye
 * @create: 2020/3/3 09:19
 */
public class ForkJoinResursiveAction {
    private static final Integer THRESHOLD = 5;
    private static AtomicInteger sum = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new ComputeRecursiveTask(0, 1000));
        forkJoinPool.awaitTermination(3, TimeUnit.SECONDS);
        Optional.of(sum).ifPresent(System.out::println);
    }

    static class ComputeRecursiveTask extends RecursiveAction {
        Integer start;
        Integer end;

        public ComputeRecursiveTask(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start > THRESHOLD) {
                // 拆分
                int mid = (end + start) / 2;
                ComputeRecursiveTask leftResult = new ComputeRecursiveTask(start, mid);
                ComputeRecursiveTask rightResult = new ComputeRecursiveTask(mid + 1, end);

                leftResult.fork();
                rightResult.fork();

            } else {
                // 计算
                sum.addAndGet(IntStream.rangeClosed(start, end).sum());
            }
        }
    }

}

