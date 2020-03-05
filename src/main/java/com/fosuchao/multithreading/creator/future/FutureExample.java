package com.fosuchao.multithreading.creator.future;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Description: 使用Future实现异步线程
 * @Auther: Joker Ye
 * @Date: 2020/2/4 09:43
 */
public class FutureExample {
    public static void main(String[] args) throws Exception{
        Callable demo = new Demo();
        FutureTask<Integer> integerFutureTask = new FutureTask<Integer>(demo);
        new Thread(integerFutureTask).start();
        doOtherTask();
        long start = System.currentTimeMillis();
        System.out.println("线程执行完成，返回结果为：" + integerFutureTask.get());
        long end = System.currentTimeMillis();
        System.out.println("取回结果的时间为：" + (end - start) + "ms");
    }

    public static void doOtherTask() throws InterruptedException {
        System.out.println("正在做干别的事情");
        Thread.sleep(1000);
    }
}


class Demo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        // 线程中执行的逻辑
        System.out.println("计算结果中。。。");
        Thread.sleep(2000);
        return 1;
    }
}