package com.qatix.base.quartz;

import com.qatix.base.quartz.job.StatefulDumbJob;
import lombok.extern.java.Log;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;


/**
 * @Author: Logan.Tang
 * @Date: 2018/11/1 11:41 AM
 * @see http://www.quartz-scheduler.org/documentation/quartz-2.x/examples/Example4.html
 */
@Log
public class MisfireExample {

    public static void main(String[] args) throws SchedulerException, InterruptedException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail job1 = JobBuilder.newJob(StatefulDumbJob.class)
                .withIdentity("statefulJob1", "group1")
                .usingJobData(StatefulDumbJob.EXECUTION_DELAY, 10000L)
                .build();

        Date runTime = new Date(System.currentTimeMillis() + 5000);
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .startAt(runTime)
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(3)
                        .repeatForever())
                .build();
        scheduler.scheduleJob(job1, trigger);


        JobDetail job2 = JobBuilder.newJob(StatefulDumbJob.class)
                .withIdentity("statefulJob2", "group1")
                .usingJobData(StatefulDumbJob.EXECUTION_DELAY, 10000L)
                .build();

        trigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1")
                .startAt(runTime)
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(3)
                        .repeatForever().withMisfireHandlingInstructionNowWithExistingCount())
                .build();
        scheduler.scheduleJob(job2, trigger);

        log.info("job schedule at " + new Date());
        scheduler.start();

        Thread.sleep(60L * 1000L);

        scheduler.shutdown(true);

    }
}
