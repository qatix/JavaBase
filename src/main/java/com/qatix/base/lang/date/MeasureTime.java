package com.qatix.base.lang.date;

import com.google.common.base.Stopwatch;

import java.time.Duration;
import java.time.Instant;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/2 10:22 AM
 */
public class MeasureTime {
    private static void way1UseStopWatch() throws InterruptedException {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();

        Thread.sleep(212);

        stopwatch.stop();
        System.out.println(stopwatch.elapsedMillis());
    }

    private static void way2UseSysMilli(){
        Long start = System.currentTimeMillis();

        try {
            Thread.sleep(212);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long end = System.currentTimeMillis();

        Long timecost = end - start;
        System.out.println(timecost);
    }

    private static void way3UseSysNano(){
        Long start = System.nanoTime();

        try {
            Thread.sleep(212);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long end = System.nanoTime();

        Long timecost = end - start;
        System.out.println(timecost);
    }

    private static void way4UseInstant(){
        Instant start = Instant.now();

        try {
            Thread.sleep(212);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant end = Instant.now();

        Long timeElapsed = Duration.between(start,end).toMillis();
        System.out.println(timeElapsed);
    }

    public static void main(String[] args) throws Exception {
        /**
         * 说明，经多次测试
         * 以下任意一种方法都有毫秒级别的差异，因为统计方法本身也需要消耗时间
         * 一次结果：
         * 216
         * 215
         * 215636141
         * 217
         */
        way1UseStopWatch();
        way2UseSysMilli();
        way3UseSysNano();
        way4UseInstant();
    }
}
