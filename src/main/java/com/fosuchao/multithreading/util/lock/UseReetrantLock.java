package com.fosuchao.multithreading.util.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 使用实现Lock接口的ReentrantLock来保证原子操作
 * 比起synchronized更加灵活，更好的实现公平性
 * @Auther: Joker Ye
 * @Date: 2020/2/4 12:57
 * @noinspection Duplicates
 */
public class UseReetrantLock {
    private ReentrantLock lock = new ReentrantLock();
    private int i = 0;

    public static void main(String[] args) {
        UseReetrantLock useReetrantLock = new UseReetrantLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.print(Thread.currentThread().getName() + "->");
                    System.out.println(useReetrantLock.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.print(Thread.currentThread().getName() + "->");
                    System.out.println(useReetrantLock.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public int getNext() {
        lock.lock();
        i++;
        lock.unlock();
        return i;
    }
}
