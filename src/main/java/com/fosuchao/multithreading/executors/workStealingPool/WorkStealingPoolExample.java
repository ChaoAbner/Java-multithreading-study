package com.fosuchao.multithreading.executors.workStealingPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: WorkStealingPool 精灵线程
 * @author: Joker Ye
 * @create: 2020/2/27 13:45
 */
public class WorkStealingPoolExample {
    // 不传参数，默认线程数是主机cpu核心数
    static ExecutorService executor = Executors.newWorkStealingPool();

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "工作中");
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "工作完成！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
       // 由于产生的是精灵线程（守护线程、后台线程），主程序不阻塞的话看不到打印信息
        while (true) {

        }
    }
}


