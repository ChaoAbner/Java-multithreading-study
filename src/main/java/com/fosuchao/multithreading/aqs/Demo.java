package com.fosuchao.multithreading.aqs;

import java.util.stream.Stream;

/**
 * @Description:
 * @Auther: Joker Ye
 * @Date: 2020/2/6 09:55
 */
public class Demo {
    private int num;
    private MyAQS lock = new MyAQS();

    public static void main(String[] args) {
        Demo demo = new Demo();
        // 测试同步
//        Stream.of("t1", "t2", "t3").forEach(name -> {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    while (true) {
//                        demo.next();
//                        try {
//                            Thread.sleep(50);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }, name).start();
//        });

        // 测试可重入
        demo.a();

    }

    public int next() {
        lock.lock();
        num++;
        System.out.println(num);
        lock.unlock();
        return num;
    }

    public void a() {
        lock.lock();
        System.out.println("a");
        b();
        lock.unlock();
    }

    public void b() {
        lock.lock();
        System.out.println("b");
        lock.unlock();
    }
}
