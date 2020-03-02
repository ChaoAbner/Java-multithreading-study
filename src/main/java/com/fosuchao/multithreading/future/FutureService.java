package com.fosuchao.multithreading.future;

import java.util.function.Consumer;

/**
 * @description: 桥接FutureTask和Future
 * @author: Joker Ye
 * @create: 2020/3/1 23:32
 */

public class FutureService {

    /**
     * 采用get阻塞方式获取结果
     * @Param futureTask
     * @return com.fosuchao.multithreading.future.Future<V>
     */
    public <V> Future<V> submit(FutureTask<V> futureTask) {
        AsyncFuture<V> future = new AsyncFuture<>();
        new Thread(() -> {
            // 工作线程处理任务
            future.done(futureTask.call());
        }).start();
        return future;
    }

    /**
     * 采用异步回调的方式获取处理结果 -> callback
     * @Param [futureTask]
     * @return com.fosuchao.multithreading.future.Future<V>
     */
    public <V> void submit(FutureTask<V> futureTask, Consumer<V> consumer) {
        AsyncFuture<V> future = new AsyncFuture<>();
        new Thread(() -> {
            // 工作线程处理任务
            V result = futureTask.call();
            future.done(result);

            // 处理完成后，交给回调函数处理
            consumer.accept(result);
        }).start();
    }
}
