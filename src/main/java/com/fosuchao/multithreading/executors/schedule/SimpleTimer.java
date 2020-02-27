package com.fosuchao.multithreading.executors.schedule;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @description: 使用JDK中的Timer实现定时任务
 * 注意：jdk原生的timer，如果任务的执行时间大于了定时时间，那么任务将会被推迟。
 * @author: Joker Ye
 * @create: 2020/2/27 14:42
 */
public class SimpleTimer {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask(){
            @Override
            public void run() {
                try {
                    System.out.println("working...");
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }
}
