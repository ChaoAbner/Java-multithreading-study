package com.fosuchao.multithreading.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: Condition解决特定唤醒某个线程的功能
 * 使用普通的notify会随机的唤醒某个线程，而notifyAll会唤醒所有线程
 * @Auther: Joker Ye
 * @Date: 2020/2/6 08:55
 */
public class Demo {
    private int num = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition bCondition = lock.newCondition();
    private Condition aCondition = lock.newCondition();
    private Condition cCondition = lock.newCondition();

    public static void main(String[] args) {
        Demo demo = new Demo();
        new Thread(new A(demo)).start();
        new Thread(new B(demo)).start();
        new Thread(new C(demo)).start();
    }

    public void a() throws InterruptedException {
        lock.lock();
        while (num != 0) {
            aCondition.await();
        }
        System.out.println("a执行");
        num++;
        bCondition.signal();
        lock.unlock();

    }

    public void b() throws InterruptedException {
        lock.lock();
        while (num != 1) {
            aCondition.await();
        }
        System.out.println("b执行");
        num++;
        cCondition.signal();
        lock.unlock();

    }

    public void c() throws InterruptedException {
        lock.lock();
        while (num != 2) {
            aCondition.await();
        }
        System.out.println("c执行");
        num = 0;
        aCondition.signal();
        lock.unlock();
    }

}

class A implements Runnable{
    Demo demo;

    A(Demo demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        try {
            while (true) {
                demo.a();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class B implements Runnable{
    Demo demo;

    public B(Demo demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        try {
            while (true) {
                demo.b();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class C implements Runnable{
    Demo demo;

    public C(Demo demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        try {
            while (true) {
                demo.c();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}