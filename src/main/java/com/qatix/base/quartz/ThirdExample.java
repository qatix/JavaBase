package com.qatix.base.quartz;

import com.qatix.base.quartz.job.SimpleJob;
import lombok.extern.java.Log;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;


/**
 * @Author: Logan.Tang
 * @Date: 2018/11/1 11:41 AM
 */
@Log
public class ThirdExample {

    public static void main(String[] args) throws SchedulerException, InterruptedException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail job1 = JobBuilder.newJob(SimpleJob.class).withIdentity("job1", "group1").build();

        //every 20 seconds
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?")).build();

        scheduler.scheduleJob(job1, trigger);

        //every other minute,start at 15 seconds past the minute
        JobDetail job2 = JobBuilder.newJob(SimpleJob.class).withIdentity("job2", "group1").build();
        trigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("15 0/2 * * * ?")).build();

        scheduler.scheduleJob(job2, trigger);

        //every other minute,between 2pm-3pm
        JobDetail job3 = JobBuilder.newJob(SimpleJob.class).withIdentity("job3", "group1").build();
        trigger = TriggerBuilder.newTrigger().withIdentity("trigger3", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 14-15 * * ?")).build();

        scheduler.scheduleJob(job3, trigger);

        //every 3 minute,between 14-23
        JobDetail job4 = JobBuilder.newJob(SimpleJob.class).withIdentity("job4", "group1").build();
        trigger = TriggerBuilder.newTrigger().withIdentity("trigger4", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/3 14-23 * * ?")).build();
        scheduler.scheduleJob(job4, trigger);

        //run at 10am on 1st and 15th days of the month
        JobDetail job5 = JobBuilder.newJob(SimpleJob.class).withIdentity("job5", "group1").build();
        trigger = TriggerBuilder.newTrigger().withIdentity("trigger5", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 10am 1,15 * ?")).build();
        scheduler.scheduleJob(job5, trigger);

        //every seconds on weekdays (monday through friday)
        JobDetail job6 = JobBuilder.newJob(SimpleJob.class).withIdentity("job6", "group1").build();
        trigger = TriggerBuilder.newTrigger().withIdentity("trigger6", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0,30 * * ? * MON-FRI")).build();
        scheduler.scheduleJob(job6, trigger);

        JobDetail job7 = JobBuilder.newJob(SimpleJob.class).withIdentity("job7", "group1").build();
        trigger = TriggerBuilder.newTrigger().withIdentity("trigger7", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0,30 * * ? * SAT,SUN")).build();
        scheduler.scheduleJob(job7, trigger);

        log.info("job schedule at " + new Date());
        scheduler.start();

        Thread.sleep(3000L * 1000L);
        scheduler.shutdown(true);
    }
}
