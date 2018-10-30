package com.qatix.base.redisson;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

/**
 * java.lang.NoSuchMethodError: io.netty.util.internal.StringUtil.indexOfNonWhiteSpace(Ljava/lang/CharSequence;I)I
 * 异常解决：
 * https://www.cnblogs.com/tanglc/p/9381280.html
 */
public class GetsetExample {
    public static void main(String[] args) {

        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://127.0.0.1:6379");

        RedissonClient client = Redisson.create(config);
        RBucket<Object> rBucket = client.getBucket("rkey");
        System.out.println("get:" + rBucket.get());

        RAtomicLong longObject = client.getAtomicLong("myLong");
        // sync way
        System.out.println(longObject.get());
        longObject.compareAndSet(3, 401);
        System.out.println(longObject.get());
        // async way
        longObject.compareAndSetAsync(3, 401);
        System.out.println(longObject.get());

    }
}
