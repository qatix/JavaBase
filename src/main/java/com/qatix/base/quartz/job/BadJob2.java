package com.qatix.base.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/1 2:49 PM
 */
@Slf4j
public class BadJob2 implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            int zero = 0;
            int calculation = 4815 / zero;
        } catch (Exception e) {
            log.info("--- Error in job1");
            JobExecutionException e2 = new JobExecutionException(e);
            e2.setUnscheduleAllTriggers(true);
            throw e2;
        }
    }
}
