package com.qatix.base.quartz;

import com.qatix.base.quartz.job.ColorJob;
import lombok.extern.java.Log;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;


/**
 * http://www.quartz-scheduler.org/documentation/quartz-2.x/examples/Example4.html
 * @Author: Logan.Tang
 * @Date: 2018/11/1 11:41 AM
 */
@Log
public class ParamsAndStateExample {

    public static void main(String[] args) throws SchedulerException, InterruptedException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail job1 = JobBuilder.newJob(ColorJob.class).withIdentity("job1","group1").build();

        Date runTime = new Date(System.currentTimeMillis() + 5000);

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1")
                .startAt(runTime)
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(10)
                    .withRepeatCount(4))
                .build();
        job1.getJobDataMap().put(ColorJob.FAVORITE_COLOR,"red");
        job1.getJobDataMap().put(ColorJob.EXECUTION_COUNT,1);

        scheduler.scheduleJob(job1,trigger);


        JobDetail job2 = JobBuilder.newJob(ColorJob.class).withIdentity("job2","group1").build();

        trigger = TriggerBuilder.newTrigger().withIdentity("trigger2","group1")
                .startAt(runTime)
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(10)
                        .withRepeatCount(4))
                .build();
        job2.getJobDataMap().put(ColorJob.FAVORITE_COLOR,"black");
        job2.getJobDataMap().put(ColorJob.EXECUTION_COUNT,1);
        scheduler.scheduleJob(job2,trigger);

        log.info("job schedule at " + new Date());
        scheduler.start();

        Thread.sleep(60L * 1000L);

        scheduler.shutdown(true);

    }
}
