package com.fosuchao.multithreading.creator.future;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Description: 使用Future实现异步线程
 * @Auther: Joker Ye
 * @Date: 2020/2/4 09:43
 */
public class UseFuture {
    public static void main(String[] args) throws Exception{
        Callable demo = new Demo();
        FutureTask<Integer> integerFutureTask = new FutureTask<Integer>(demo);
        new Thread(integerFutureTask).start();
        System.out.println("干别的事情");
        System.out.println("线程执行完成，返回结果为：" + integerFutureTask.get());
    }
}


class Demo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        // 线程中执行的逻辑
        System.out.println("计算结果中。。。");
        Thread.sleep(1000);
        return 1;
    }
}