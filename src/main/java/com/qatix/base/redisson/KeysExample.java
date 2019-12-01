package com.qatix.base.redisson;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

public class KeysExample {

    public static void main(String[] args) {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://127.0.0.1:6379");

        RedissonClient client = Redisson.create(config);

        RKeys keys = client.getKeys();
        System.out.println("total-count:" + keys.count());

        Iterable<String> allKeys = keys.getKeys();
        for (String key : allKeys) {
            System.out.println(key);
        }

        Iterable<String> foundedKeys = keys.getKeysByPattern("store*", 5);
        for (String key : foundedKeys) {
            System.out.println("by-patern:" + key);
        }
    }
}
