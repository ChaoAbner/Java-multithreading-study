package com.fosuchao.multithreading.concurrent.util.exchanger;

import java.util.concurrent.Exchanger;

/**
 * @Description:
 * @Auther: Joker Ye
 * @Date: 2020/2/6 10:47
 */
public class ExchangerSimple {
    static Exchanger<Integer> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(new WorkerA(exchanger)).start();
        new Thread(new WorkerB(exchanger)).start();
    }
}

class WorkerA implements Runnable {
    Exchanger<Integer> exchanger;

    public WorkerA(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        System.out.println("wokerA工作中。。");
        try {
            Thread.sleep(1000);
            Integer exchange = exchanger.exchange(1);
            System.out.println("A收到B的报告，B的工作量为：" + exchange);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WorkerB implements Runnable {
    Exchanger<Integer> exchanger;

    public WorkerB(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        System.out.println("wokerB工作中。。");
        try {
            Thread.sleep(2000);
            Integer exchange = exchanger.exchange(2);
            System.out.println("B收到A的报告，A的工作量为：" + exchange);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}