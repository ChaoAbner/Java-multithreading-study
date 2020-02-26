package com.fosuchao.multithreading.concurrent.util.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @Description: 使用Semaphore实现一个锁
 * @Auther: Joker Ye
 * @Date: 2020/2/6 10:47
 */
public class SemaphoreLock {
    // 构造函数参数为指定的信号量/许可证，每次线程进入可以获取
    static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        new Thread(new Work(semaphore, 1)).start();
        new Thread(new Work(semaphore, 1)).start();
    }
}

class Work implements Runnable {
    Semaphore semaphore;
    Integer permits;

    public Work(Semaphore semaphore, Integer permits) {
        this.semaphore = semaphore;
        this.permits = permits;
    }

    @Override
    public void run() {
        String currThread = Thread.currentThread().getName();
        try {
            semaphore.acquire(permits);
            System.out.println(currThread + "获取了锁");
            System.out.println(currThread + "正在工作");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            System.out.println(currThread + "释放了锁");
        }
    }
}