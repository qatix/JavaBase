package com.qatix.base.quartz;

import com.qatix.base.quartz.job.BadJob1;
import com.qatix.base.quartz.job.BadJob2;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/1 3:05 PM
 */
@Slf4j
public class ExceptionExample {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        //会一直尝试运行
        JobDetail job1 = JobBuilder.newJob(BadJob1.class).withIdentity("badJob1", "group1").build();
        Date runTime = new Date(System.currentTimeMillis() + 5000);
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .startAt(runTime)
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(3)
                        .repeatForever())
                .build();

        //finish time
        Date ft = scheduler.scheduleJob(job1, trigger);

        //job2 异常后会取消，后续不会再继续运行
        JobDetail job2 = JobBuilder.newJob(BadJob2.class).withIdentity("badJob2", "group1").build();
        trigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1")
                .startAt(runTime)
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(3)
                        .repeatForever())
                .build();
        ft = scheduler.scheduleJob(job2, trigger);

        log.info("job schedule at " + new Date());
        scheduler.start();

        Thread.sleep(60L * 1000L);

        scheduler.shutdown(true);
    }
}
