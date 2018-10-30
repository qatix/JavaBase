package com.qatix.base.redisson;

import org.redisson.Redisson;
import org.redisson.api.RBitSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

/**
 * https://github.com/redisson/redisson/wiki/6.-%E5%88%86%E5%B8%83%E5%BC%8F%E5%AF%B9%E8%B1%A1#68-%E5%9F%BA%E6%95%B0%E4%BC%B0%E8%AE%A1%E7%AE%97%E6%B3%95hyperloglog
 */
public class BitSetExample {
    public static void main(String[] args) {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://127.0.0.1:6379");

        RedissonClient client = Redisson.create(config);
        RBitSet bset = client.getBitSet("mybitset");

        bset.set(0,true);
        bset.set(1000,false);
        System.out.println(bset.get(0));
        System.out.println(bset.get(100));

        bset.clear();
        System.out.println(bset.get(0));

        bset.andAsync("e");

        bset.xor("anotherBitSet");

        System.out.println(bset.cardinality());

//        System.out.println(new String(bset.toByteArray()));
    }
}
