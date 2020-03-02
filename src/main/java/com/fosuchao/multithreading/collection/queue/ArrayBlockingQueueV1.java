package com.fosuchao.multithreading.collection.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @description: ArrayBlockingQueue,需要指定初始容量。内部存储结构为数组
 * @author: Joker Ye
 * @create: 2020/3/2 09:50
 */
public class ArrayBlockingQueueV1 {
    // add remove
    ArrayBlockingQueue<String> blockingQueue1 = new ArrayBlockingQueue<>(5);
    // offer poll
    ArrayBlockingQueue<String> blockingQueue2 = new ArrayBlockingQueue<>(5);

    public boolean addByAdd(String s) {
        return blockingQueue1.add(s);
    }

    public boolean addByOffer(String s) {
        return blockingQueue2.offer(s);
    }

    public String removeByRemove() {
        return blockingQueue1.remove();
    }

    public String removeByPoll() {
        return blockingQueue2.poll();
    }

    public static void main(String[] args) {
        ArrayBlockingQueueV1 myQueue = new ArrayBlockingQueueV1();
        System.out.println("Adding 6 elements by offer ()");
        for (int i = 0; i < 6; i++) {
            System.out.println(
                    "Element no :" + (i + 1) + "adding by offer() : " + myQueue.addByOffer("String" + i));
        }

        System.out.println("=============================");

        System.out.println("Adding 6 elements by add ()");
        for (int i = 0; i < 5; i++) {
            System.out.println(
                    "Element no :" + (i + 1) + "adding by add() : " + myQueue.addByAdd("String" + i));
        }


        System.out.println("=============================");
        System.out.println("Removing 6 elements by poll ()");
        for (int i = 0; i < 6; i++) {
            System.out.println("Element no :" + (i+1) + " removed by poll() : " + myQueue.removeByPoll());
        }


        System.out.println("=============================");
        System.out.println("Removing 6 elements by remove ()");
        for (int i = 0; i < 6; i++) {
            System.out.println("Element no :" + (i+1) + " removed by remove() : " + myQueue.removeByRemove());
        }
    }
}
