package com.fosuchao.multithreading.concurrent.util.lock.mylock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Description: 实现Lock，可重入
 * @Auther: Joker Ye
 * @Date: 2020/2/4 13:13
 */
public class MyLock implements Lock {
    private Boolean isLocked = false;
    private int threadCount = 0;
    private Thread current = null;

    @Override
    public synchronized void lock() {
        Thread thread = Thread.currentThread();
        while (isLocked && thread != current) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "获取了锁");
        isLocked = true;
        threadCount++;
        current = thread;
    }

    @Override
    public synchronized void unlock() {
        Thread thread = Thread.currentThread();
        if (isLocked && thread == current) {
            threadCount--;
        }
        if (threadCount == 0) {
            isLocked = false;
            current = null;
            this.notifyAll();
            System.out.println(Thread.currentThread().getName() + "释放了锁");
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
