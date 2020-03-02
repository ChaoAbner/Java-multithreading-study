package com.fosuchao.multithreading.future;

/**
 * @description:
 * @author: Joker Ye
 * @create: 2020/3/1 23:30
 */
public interface FutureTask<V>{
    // 执行的任务
    V call();
}
