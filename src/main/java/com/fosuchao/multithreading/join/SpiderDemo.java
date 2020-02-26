package com.fosuchao.multithreading.join;

/**
 * @Description: join的一个多线程爬虫示例
 * @Auther: Joker Ye
 * @Date: 2020/2/7 17:19
 */
public class SpiderDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Spider1(1, 10));
        Thread t2 = new Thread(new Spider2(11, 20));
        Thread t3 = new Thread(new Spider3(21, 30));

        t1.start();
        t2.start();
        t3.start();
        // join是相对于当前线程来说的，即main等待
        // join可以传递时间参数，即多少时间后没有结束，则main不再等待，直接执行后面逻辑
//        t1.join(1);
        t1.join();
        t2.join();
        t3.join();
        // 等待所有线程的任务结束，在执行后面的逻辑
        System.out.println("数据汇总，开始清洗数据");
    }
}

class Spider1 implements Runnable {

    private Integer pageStart;
    private Integer pageEnd;

    public Spider1(Integer pageStart, Integer pageEnd) {
        this.pageStart = pageStart;
        this.pageEnd = pageEnd;
    }

    @Override
    public void run() {
        for (int i = pageStart; i < pageEnd; i++) {
            System.out.println("线程1正在爬取第" + i +"页数据");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Spider2 implements Runnable {

    private Integer pageStart;
    private Integer pageEnd;

    public Spider2(Integer pageStart, Integer pageEnd) {
        this.pageStart = pageStart;
        this.pageEnd = pageEnd;
    }

    @Override
    public void run() {
        for (int i = pageStart; i < pageEnd; i++) {
            System.out.println("线程2正在爬取第" + i +"页数据");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class Spider3 implements Runnable {

    private Integer pageStart;
    private Integer pageEnd;

    public Spider3(Integer pageStart, Integer pageEnd) {
        this.pageStart = pageStart;
        this.pageEnd = pageEnd;
    }

    @Override
    public void run() {
        for (int i = pageStart; i < pageEnd; i++) {
            System.out.println("线程3正在爬取第" + i +"页数据");
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}