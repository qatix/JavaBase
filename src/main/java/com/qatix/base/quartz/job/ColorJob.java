package com.qatix.base.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.util.Date;

/**
 *@see http://www.quartz-scheduler.org/documentation/quartz-2.x/examples/Example4.html
 * @Author: Logan.Tang
 * @Date: 2018/11/1 2:06 PM
 */
@Slf4j
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ColorJob implements Job {

    public static final String FAVORITE_COLOR = "favorite-color";
    public static final String EXECUTION_COUNT = "execution-count";

    private int localCounter = 0;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        JobDataMap data = context.getJobDetail().getJobDataMap();
        String favoriteColor = data.getString(FAVORITE_COLOR);
        int count = data.getInt(EXECUTION_COUNT);
        log.info("ColorJob:" + jobKey + " executing at " + new Date() + "\n" +
                " favorite color is " + favoriteColor + "\n" +
                " execution count (from job map) is " + count + "\n" +
                " execution count (from job member variable) is " + localCounter);

        localCounter++;
        count++;
        data.put(EXECUTION_COUNT, count);
    }
}
