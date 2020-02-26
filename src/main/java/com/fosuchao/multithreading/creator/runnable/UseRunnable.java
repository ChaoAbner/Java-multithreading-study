package com.fosuchao.multithreading.creator.runnable;

/**
 * @Description: 使用实现runnable接口
 * @Auther: Joker Ye
 * @Date: 2020/2/4 09:15
 */
public class UseRunnable {
    public static void main(String[] args) {
        Runnable thread1 = new Thread1();
        Runnable thread2 = new Thread1();

        new Thread(thread1).start();
        new Thread(thread2).start();
    }
}


/** @noinspection Duplicates*/
class Thread1 implements Runnable {

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
class Thread2 implements Runnable {

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