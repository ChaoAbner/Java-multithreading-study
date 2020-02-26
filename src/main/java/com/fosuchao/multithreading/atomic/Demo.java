package com.fosuchao.multithreading.atomic;

import com.fosuchao.multithreading.atomic.entity.User;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Description: 使用原子类(java.util.concurrent.atomic包中)保证操作的原子性
 * 主要有四类
 * 原子更新基本类型
 * 原子更新数组
 * 原子更新字段
 * 原子更新引用类型
 * @Auther: Joker Ye
 * @Date: 2020/2/4 10:36
 * @noinspection Duplicates
 */
public class Demo {

    public static void main(String[] args) {
        OrdinarySequence ordinarySequence = new OrdinarySequence();
        IntegerSequence integerSequence = new IntegerSequence();
        ArraySequence arraySequence = new ArraySequence();
        QuoteSequence quoteSequence = new QuoteSequence();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.print(Thread.currentThread().getName());
                    System.out.print(" 普通：" + ordinarySequence.getAndIncrement());
                    System.out.print(" atomicInteger:" + integerSequence.getAndIncrement());
                    arraySequence.update();
                    System.out.println(" AtomicIntegerFieldUpdater" + quoteSequence.getAndIncrement());
                    try {
                        Thread.sleep(1000);
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
                    System.out.print(Thread.currentThread().getName());
                    System.out.print("普通：" + ordinarySequence.getAndIncrement());
                    System.out.print("atomicInteger:" + integerSequence.getAndIncrement());
                    arraySequence.update();
                    System.out.println("AtomicIntegerFieldUpdater:" + quoteSequence.getAndIncrement());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

class OrdinarySequence {
    // int类型，会出现线程安全问题
    private int num;

    public int getAndIncrement() {
        return num++;
    }
}

class IntegerSequence {
    // 采用原子整形，保证自增的原子性
    private AtomicInteger num = new AtomicInteger(0);

    public int getAndIncrement() {
        return num.getAndIncrement();
    }
}

class ArraySequence {
    private int[] array = {1, 2, 3, 4, 5};
    private AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(array);

    public void update() {
        atomicIntegerArray.getAndIncrement(0);
        System.out.print("automicIntegerArray:" + atomicIntegerArray.toString());
    }
}

class QuoteSequence {
    User user = new User();
    AtomicIntegerFieldUpdater<User> age =
            AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

    public int getAndIncrement() {
        age.getAndIncrement(user);
        return user.getAge();
    }
}