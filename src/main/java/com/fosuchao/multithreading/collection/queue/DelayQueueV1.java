package com.fosuchao.multithreading.collection.queue;

import lombok.Data;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @description: DelayQueue底层使用PriorityQueue进行存储元素
 * DelayQueue中的元素需要实现Delayed接口，getDelay和CompareTo方法
 * @author: Joker Ye
 * @create: 2020/3/2 09:52
 */
public class DelayQueueV1 {
    public static void main(String[] args) {
        DelayQueue delayQueue = new DelayQueue();
        new Thread(new Producer(delayQueue)).start();
        new Thread(new Consumer(delayQueue)).start();
    }
}

class Consumer implements Runnable {
    DelayQueue delayQueue;

    public Consumer(DelayQueue delayQueue) {
        this.delayQueue = delayQueue;
    }

    public void run() {
        try {
            while (true) {
                Email email = (Email) delayQueue.take();
                System.out.println("[消费者] 发送邮箱" + email);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Producer implements Runnable {
    private final Random random = new Random();

    private static final String EMAIL_TEXT = "Email body text with delay :: ";

    BlockingQueue delayQueue;

    public Producer(DelayQueue delayQueue) {
        this.delayQueue = delayQueue;
    }

    public void run() {
        try {
            while (true) {
                String to = UUID.randomUUID().toString() + "@qq.com";
                long delay = random.nextInt(5000);
                String text = EMAIL_TEXT + delay;
                Email email = new Email(to, text, delay);
                delayQueue.put(email);
                System.out.println("[生产者] 添加邮箱任务" + email);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

@Data
class Email implements Delayed {
    private String receipient;
    private String mailBody;
    private long startTime;

    public Email(String receipient, String body, long delay) {
        this.receipient = receipient;
        this.mailBody = body;
        this.startTime = System.currentTimeMillis() + delay;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this.startTime < ((Email) o).startTime) {
            return -1;
        }
        if (this.startTime > ((Email) o).startTime) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Email [receipient=" + receipient + ", mailBody=" + mailBody + ", startTime=" + startTime + "]";
    }
}