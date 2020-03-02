package com.fosuchao.multithreading.util.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description: Exchanger循环交换
 * @author: Joker Ye
 * @create: 2020/2/26 17:04
 */
public class ExchangerCycle {
    static Exchanger<AtomicReference> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(new PlayerA(exchanger)).start();
        new Thread(new PlayerB(exchanger)).start();
    }
}

class PlayerA implements Runnable {
    Exchanger<AtomicReference> exchanger;

    public PlayerA(Exchanger<AtomicReference> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        AtomicReference reference = new AtomicReference<>("A的玩具");

        try {
            while (true) {
                reference = exchanger.exchange(reference);
                System.out.println("A收到了：" + reference.get());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class PlayerB implements Runnable {
    Exchanger<AtomicReference> exchanger;

    public PlayerB(Exchanger<AtomicReference> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        AtomicReference reference = new AtomicReference<>("B的玩具");

        try {
            while (true) {
                reference = exchanger.exchange(reference);
                System.out.println("B收到了：" + reference.get());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}