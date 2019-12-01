package com.qatix.base.log.lombok;

import lombok.extern.slf4j.Slf4j;

/**
 * 什么用Slf4j替代log4j
 * https://www.oschina.net/translate/why-use-sl4j-over-log4j-for-logging
 *
 * @Author: Logan.Tang
 * @Date: 2018/10/29 5:14 PM
 */
@Slf4j
public class LogTest {
    public static void main(String[] args) {

        System.out.println("isDebugEnabled:" + log.isDebugEnabled());
        System.out.println("isInfoEnabled:" + log.isInfoEnabled());
        System.out.println("isWarnEnabled:" + log.isWarnEnabled());
        System.out.println("isErrorEnabled:" + log.isErrorEnabled());

        log.trace("this is info");
        log.trace("this is info with params : {}", 123);

        log.info("this is info");
        log.info("this is info with params : {}", 123);

        log.warn("this is warning");
        log.warn("this is warning with params : {}", 123);

        log.error("this is warning");
        log.error("this is warning with params : {}", 123);

        log.debug("this is warning");
        log.debug("this is warning with params : {}", 123);
    }
}
/*
    isDebugEnabled:false
isInfoEnabled:true
isWarnEnabled:true
isErrorEnabled:true
[INFO ] 2018-10-29 17:55:33 LogTest@(LogTest.java:21):this is info
[INFO ] 2018-10-29 17:55:33 LogTest@(LogTest.java:22):this is info with params : 123
[WARN ] 2018-10-29 17:55:33 LogTest@(LogTest.java:24):this is warning
[WARN ] 2018-10-29 17:55:33 LogTest@(LogTest.java:25):this is warning with params : 123
[ERROR] 2018-10-29 17:55:33 LogTest@(LogTest.java:27):this is warning
[ERROR] 2018-10-29 17:55:33 LogTest@(LogTest.java:28):this is warning with params : 123
 */