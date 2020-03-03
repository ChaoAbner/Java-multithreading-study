package com.fosuchao.multithreading.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * @description: RecursiveTask计算有返回值
 * @author: Joker Ye
 * @create: 2020/3/3 09:02
 */
public class ForkJoinRecursiveTask {
    private static final Integer THRESHOLD = 5;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> task = forkJoinPool.submit(new ComputeRecursiveTask(0, 1000));
        System.out.println(task.get());
    }

    static class ComputeRecursiveTask extends RecursiveTask<Integer> {
        Integer start;
        Integer end;

        public ComputeRecursiveTask(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start > THRESHOLD) {
                // 拆分
                int mid = (end + start) / 2;
                ComputeRecursiveTask leftResult = new ComputeRecursiveTask(start, mid);
                ComputeRecursiveTask rightResult = new ComputeRecursiveTask(mid + 1, end);

                leftResult.fork();
                rightResult.fork();

                return leftResult.join() + rightResult.join();
            } else {
                // 计算
                return IntStream.rangeClosed(start, end).sum();
            }
        }
    }
}
