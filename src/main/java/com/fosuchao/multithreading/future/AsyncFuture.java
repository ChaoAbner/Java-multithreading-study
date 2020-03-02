package com.fosuchao.multithreading.future;


/**
 * @description:
 * @author: Joker Ye
 * @create: 2020/3/1 23:35
 */
public class AsyncFuture<V> implements Future<V> {
    // 任务完成标志
    private volatile Boolean done = false;
    // 执行结果
    private V result;

    @Override
    public V get() throws InterruptedException {
        synchronized (this) {
            while (!done) {
               this.wait();
            }
        }
        return result;
    }

    public void done(V result) {
        synchronized (this) {
            this.result = result;
            this.done = true;
            this.notifyAll();
        }
    }
}
