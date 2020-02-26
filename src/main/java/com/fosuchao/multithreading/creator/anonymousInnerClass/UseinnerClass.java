package com.fosuchao.multithreading.creator.anonymousInnerClass;

/**
 * @Description: 使用匿名内部类的方式
 * 1、重写Thread的run方法 2、实现Runnable中的run方法
 * 当又重写又实现的时候，则使用自类重写的run方法。
 * @Auther: Joker Ye
 * @Date: 2020/2/4 09:20
 */
public class UseinnerClass {
    public static void main(String[] args) {
        new Thread1().runThread();
        new Thread2().runThread();
        new Thread3().runThread();
    }
}

/** @noinspection Duplicates*/
class Thread1 {
    public void runThread() {
        new Thread(){
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}

/** @noinspection Duplicates*/
class Thread2 {
    public void runThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

/** @noinspection Duplicates*/
class Thread3 {
    public void runThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.print("runnable");
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }) {
            // 重写父类run方法
            public void run() {
                while (true) {
                    System.out.print("sub");
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}