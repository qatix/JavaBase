package com.qatix.base.redisson;

import org.redisson.Redisson;
import org.redisson.api.RHyperLogLog;
import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

import java.util.Arrays;

public class HyperLogLogExample {

    public static void main(String[] args) {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://127.0.0.1:6379");

        RedissonClient client = Redisson.create(config);

        RHyperLogLog<String> log = client.getHyperLogLog("log");
        log.add("abc");
        log.add("ccd");
        log.add("ccc");
        String[] arr = new String[]{"a111","b222","c333"};
        log.addAll(Arrays.asList(arr));
        System.out.println(log.count());

        RHyperLogLog<String> log2 = client.getHyperLogLog("log2");
        log2.add("111");
        log2.add("222");
        log2.add("ccc");
        System.out.println(log2.count());

        System.out.println("count with log2:" + log.countWith("log2"));

        log.mergeWith("log2");
        System.out.println("after merge:" + log.count());
    }
}
