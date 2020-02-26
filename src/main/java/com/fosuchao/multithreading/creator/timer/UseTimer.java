package com.fosuchao.multithreading.creator.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Description: 定时器开启一个线程任务，定时执行
 * @Auther: Joker Ye
 * @Date: 2020/2/4 10:06
 */
public class UseTimer {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时器任务执行。。");
            }
        }, 0, 1000);
    }
}


