package com.fosuchao.multithreading.theadlocal;

import java.util.Random;

/**
 * @description:
 * @author: Joker Ye
 * @create: 2020/2/26 10:24
 */
public class ThreadLocalTest {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(new MyRunnable(threadLocal), "t-"+i).start();
        }
    }
}

class MyRunnable implements Runnable {

    ThreadLocal<String> threadLocal;

    public MyRunnable(ThreadLocal<String> threadLocal) {
        this.threadLocal = threadLocal;
    }

    @Override
    public void run() {
        this.threadLocal.set("value of " + Thread.currentThread().getName());
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(Thread.currentThread().getName()+"的值为 -> ");
        System.out.println(this.threadLocal.get());
    }
}