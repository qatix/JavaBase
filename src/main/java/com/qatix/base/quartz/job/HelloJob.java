package com.qatix.base.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/1 1:44 PM
 */
@Slf4j
public class HelloJob implements Job {
    public HelloJob() {
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("hello job! - " + new Date());
    }
}
