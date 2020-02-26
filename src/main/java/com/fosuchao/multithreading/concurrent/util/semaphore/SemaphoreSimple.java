package com.fosuchao.multithreading.concurrent.util.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @description:
 * @author: Joker Ye
 * @create: 2020/2/26 17:50
 */
public class SemaphoreSimple {
    static Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {
//        new Thread(new Work(semaphore, 2)).start();
        new Thread(new Work(semaphore, 1)).start();
        new Thread(new Work(semaphore, 1)).start();
    }
}


class Work1 implements Runnable {
    Semaphore semaphore;
    Integer permits;

    public Work1(Semaphore semaphore, Integer permits) {
        this.semaphore = semaphore;
        this.permits = permits;
    }

    @Override
    public void run() {
        String currThread = Thread.currentThread().getName();
        try {
            semaphore.acquire(permits);
            System.out.println(currThread + "正在工作");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}