package com.fosuchao.multithreading.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Description: 使用AQS实现自己的锁
 * @Auther: Joker Ye
 * @Date: 2020/2/6 09:43
 */
public class MyAQS implements Lock {
    private Helper helper = new Helper();

    private class Helper extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            // 尝试加锁
            // 1、第一个线程进入，可以获得锁
            // 2、同一个线程多次进入，可以获得锁
            // 3、不同的线程进入，不可用获得锁
            int state = getState();
            Thread current = Thread.currentThread();
            if (state == 0) {
                if (compareAndSetState(0, arg)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            } else if (current == getExclusiveOwnerThread()){
                // 重入锁
                setState(state + arg);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            Thread thread = Thread.currentThread();
            if (thread != getExclusiveOwnerThread()) {
                throw new RuntimeException();
            }
            int newState = getState() - arg;
            boolean flag = false;

            if (newState == 0) {
                setExclusiveOwnerThread(null);
                flag = true;
            }
            setState(newState);
            return flag;
        }

        public Condition getCondition() {
            return new ConditionObject();
        }
    }

    @Override
    public void lock() {
        helper.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        helper.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return helper.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return helper.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        helper.release(1);
    }

    @Override
    public Condition newCondition() {
        return helper.getCondition();
    }
}
