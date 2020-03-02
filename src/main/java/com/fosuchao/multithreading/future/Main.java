package com.fosuchao.multithreading.future;

/**
 * @description:
 * @author: Joker Ye
 * @create: 2020/3/1 23:42
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        FutureService service = new FutureService();
        Future<String> future = service.submit(() -> {
            try {
                System.out.println("service 执行耗时操作中");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "finish";
        });

        Thread.sleep(2000);
        System.out.println("do order thing..");
        long start = System.currentTimeMillis();
        System.out.println(future.get());
        long end = System.currentTimeMillis();
        System.out.println("花费时间：" + (end - start) + "毫秒");


        // 异步回调future结果，使用Consumer来进行结果处理，这里为System.out::println
        FutureService service1 = new FutureService();
        service1.submit(() -> {
            try {
                System.out.println("service1 执行耗时操作中");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "finish";
        }, System.out::println);

    }
}
