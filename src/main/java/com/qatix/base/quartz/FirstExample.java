package com.qatix.base.quartz;

import com.qatix.base.quartz.job.HelloJob;
import lombok.extern.java.Log;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;


/**
 * @Author: Logan.Tang
 * @Date: 2018/11/1 11:41 AM
 */
@Log
public class FirstExample {

    public static void main(String[] args) throws SchedulerException, InterruptedException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();

        Date runTime = new Date(System.currentTimeMillis() + 5000);

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .startAt(runTime).build();

        scheduler.scheduleJob(jobDetail, trigger);

        log.info("job schedule at " + new Date());
        scheduler.start();

        Thread.sleep(30L * 1000L);

        scheduler.shutdown(true);

    }
}
