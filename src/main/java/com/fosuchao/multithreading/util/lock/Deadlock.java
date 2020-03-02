package com.fosuchao.multithreading.util.lock;

/**
 * @Description:
 * @Auther: Joker Ye
 * @Date: 2020/2/7 12:34
 */
public class Deadlock {
    private String resourceA = "A";
    private String resourceB = "B";

    public String getResourceA() {
        return resourceA;
    }

    public String getResourceB() {
        return resourceB;
    }

    public static void main(String[] args) {
        Deadlock deadlock = new Deadlock();
        new Thread(new A(deadlock)).start();
        new Thread(new B(deadlock)).start();
    }

}


class A implements Runnable {
    private Deadlock deadlock;

    public A(Deadlock deadlock) {
        this.deadlock = deadlock;
    }

    @Override
    public void run() {
        synchronized (deadlock.getResourceB()) {
            try {
                System.out.println(Thread.currentThread().getName() + "拿到资源B");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "尝试拿到资源A");
            synchronized (deadlock.getResourceA()) {
                System.out.println(Thread.currentThread().getName() + "拿到资源A");
            }
        }
    }
}

class B implements Runnable {
    private Deadlock deadlock;

    public B(Deadlock deadlock) {
        this.deadlock = deadlock;
    }

    @Override
    public void run() {
        synchronized (deadlock.getResourceA()) {
            try {
                System.out.println(Thread.currentThread().getName() + "拿到资源A");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "尝试拿到资源B");
            synchronized (deadlock.getResourceB()) {
                System.out.println(Thread.currentThread().getName() + "拿到资源B");
            }
        }
    }
}



