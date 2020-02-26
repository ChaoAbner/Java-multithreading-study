package com.fosuchao.multithreading.daemon;

/**
 * @Description: 守护线程 daemon
 * 当创建守护线程的线程中断或者结束时，守护线程就会自动销毁，所以守护线程常用于垃圾回收，心跳检测等等应用
 * @Auther: Joker Ye
 * @Date: 2020/2/7 16:34
 */
public class Demo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Super(), "t1");
        t1.start();
        Thread.sleep(3000);
        t1.interrupt();
        System.out.println(t1.isInterrupted());
        System.out.println("main结束");

    }
}


class Super implements Runnable {

    @Override
    public void run() {
        // 开启一个守护线程
        Thread daemon = new Thread(new Daemon(), "daemon");
        // setDaemon必须在start前面，不然抛异常
        daemon.setDaemon(true);
        daemon.start();
        while (!Thread.currentThread().isInterrupted()){
            System.out.println("t1线程执行中");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("t1结束");
                break;
            }
        }
    }
}

class Daemon implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("守护线程执行心跳监测");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}