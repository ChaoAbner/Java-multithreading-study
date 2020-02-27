package com.fosuchao.multithreading.executors.schedule;

import org.quartz.*;
import org.quartz.core.QuartzScheduler;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.text.ParseException;

import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @description: 使用Quartz实现定时任务，可以避免任务超时到时定时任务推迟的问题。跟linux的crontab有点像。
 * 要素
 * 1、做什么 job
 * 2、触发器（定时时间） trigger
 * 3、时间调度器
 * @author: Joker Ye
 * @create: 2020/2/27 14:56
 */
public class QuartzScheduleExample {
    public static void main(String[] args) throws ParseException, SchedulerException {
        JobDetail jobDetail = new JobDetailImpl("myJob-1",
                "group", MyJob.class);

        CronTrigger trigger = new CronTriggerImpl(
                "myJob-1", "group", "0/3 * * * * ?");

        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
