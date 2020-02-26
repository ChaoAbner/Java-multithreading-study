package com.fosuchao.multithreading.daemon;

/**
 * @Description: 利用守护线程和interrupt实现任务超时处理案例
 * @Auther: Joker Ye
 * @Date: 2020/2/7 17:40
 */
public class ThreadService {
    private Thread thread;
    private boolean finished = false;

    public void execute(Runnable task, Long timeout) {
        thread = new Thread() {
            @Override
            public void run() {
                Thread daemon = new Thread(task);
                daemon.setDaemon(true);
                daemon.start();
                try {
                    daemon.join();
                    finished = true;
                } catch (InterruptedException e) {
                    System.out.println("任务超时，中断任务");
                }
            }
        };
        thread.start();
        showdown(timeout);
    }

    public void showdown(Long millis) {
        // 设置超时时间
        Long current = System.currentTimeMillis();
        while (!finished) {
            if (System.currentTimeMillis() - current > millis) {
                // 已超时
                System.out.println("超时，准备中断任务");
                thread.interrupt();
                break;
            }
        }
    }
}

class Client {
    public static void main(String[] args) {
        ThreadService threadService = new ThreadService();
        threadService.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("一个费时任务执行中。。。");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        System.out.println("任务超时，任务结束");
                        break;
                    }
                }
            }
        }, 1000L);

    }
}
