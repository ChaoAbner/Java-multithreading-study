package com.fosuchao.multithreading.creator.thread;

/**
 * @Description: 通过继承Thread实现
 * @Auther: Joker Ye
 * @Date: 2020/2/3 22:35
 */
public class UseThread {
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        thread2.start();
    }
}

/** @noinspection Duplicates*/
class Thread1 extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/** @noinspection Duplicates*/
class Thread2 extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}