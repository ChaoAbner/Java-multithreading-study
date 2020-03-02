package com.fosuchao.multithreading.future;

/**
 * @description:
 * @author: Joker Ye
 * @create: 2020/3/1 23:29
 */
public interface Future<V> {
    // 获取返回结果
    V get() throws InterruptedException;
}
