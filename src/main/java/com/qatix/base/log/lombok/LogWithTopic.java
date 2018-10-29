package com.qatix.base.log.lombok;

import lombok.extern.slf4j.Slf4j;

/**
 * https://projectlombok.org/features/log
 * @Author: Logan.Tang
 * @Date: 2018/10/29 5:21 PM
 */
@Slf4j(topic = "MyTopic")
public class LogWithTopic {
    public static void main(String[] args) {
        log.info("this is log with topic");
        //output
        //17:22:57.041 [main] INFO MyTopic - this is log with topic
    }
}
