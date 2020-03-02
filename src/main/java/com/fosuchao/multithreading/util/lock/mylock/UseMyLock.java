package com.fosuchao.multithreading.util.lock.mylock;

/**
 * @Description: 通过继承lock来实现自己的锁
 * @Auther: Joker Ye
 * @Date: 2020/2/4 14:45
 * @noinspection Duplicates
 */
public class UseMyLock {
    private MyLock lock = new MyLock();
    private volatile int num = 0;

    public static void main(String[] args) {
        UseMyLock useMyLock = new UseMyLock();
        // 测试是否可重入
        useMyLock.a();

        // 测试是否保证原子性
//        Stream.of("T1", "T2", "T3").forEach(name -> {
//            new Thread(() -> {
//                while (true) {
//                    useMyLock.getNext();
//                    System.out.println(Thread.currentThread().getName() + " -> " + "num = " + useMyLock.num);
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
//            }, name).start();
//        });
//    }

    public void getNext() {
        lock.lock();
        num++;
        lock.unlock();
    }

    public void a() {
        lock.lock();
        System.out.println("执行a");
        b();
        lock.unlock();
    }

    public void b() {
        lock.lock();
        System.out.println("执行b");
        lock.unlock();
    }
}
