package com.qatix.base.lang.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/1 10:52 AM
 */
public class ParallelStreamExample {
    public static void main(String[] args) {

        int max = 1000000;
        List<String> values = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        long t0 = System.nanoTime();
        long count = values.stream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("Sequential sort took:%d ms", millis));


        long t2 = System.nanoTime();
        count = values.parallelStream().sorted().count();
        System.out.println(count);
        long t3 = System.nanoTime();
        millis = TimeUnit.NANOSECONDS.toMillis(t3 - t2);
        System.out.println(String.format("Parallel sort took:%d ms", millis));

    }
}

//1000000
//Sequential sort took:791 ms
//1000000
//Parallel sort took:301 ms