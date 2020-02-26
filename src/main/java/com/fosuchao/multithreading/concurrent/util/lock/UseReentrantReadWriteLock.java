package com.fosuchao.multithreading.concurrent.util.lock;


import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 读写锁， 写写互斥， 读写互斥 ，读读不互斥
 * @Auther: Joker Ye
 * @Date: 2020/2/6 10:32
 */
public class UseReentrantReadWriteLock {
    private HashMap<String, String> map = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    public String get(String key) {
        String res = null;
        readLock.lock();
        System.out.println(Thread.currentThread().getName() + "-> 读操作开始");
        if (map.containsKey(key))
            res = map.get(key);
        readLock.unlock();
        System.out.println(Thread.currentThread().getName() + "-> 读操作结束");
        return res;
    }

    public void put(String key, String value) {
        writeLock.lock();
        System.out.println(Thread.currentThread().getName() + "-> 写操作开始");
        map.put(key, value);
        writeLock.unlock();
        System.out.println(Thread.currentThread().getName() + "-> 写操作结束");
    }

    public static void main(String[] args) {
        UseReentrantReadWriteLock demo = new UseReentrantReadWriteLock();
        demo.put("k1", "v1");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(demo.get("k1"));
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
                    System.out.println(demo.get("k1"));
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
                    System.out.println(demo.get("k1"));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
