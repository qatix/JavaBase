package com.qatix.base.redis;

import org.apache.commons.lang3.time.FastDateFormat;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class KeysTest {

    private static final String redisHost = "localhost";

    private static final int redisPort = 6379;
    private static final String redisPassword = "";

    public static void main(String[] args) {
        Jedis jedis = new Jedis(redisHost, redisPort, 60000);
        if (redisPassword.length() > 0) {
            jedis.auth(redisPassword);
        }

        Set<String> names = jedis.keys("bill_date:*:d:*");
        System.out.println(names.size());

        String todayStr = FastDateFormat.getInstance("yyyyMMdd").format(new Date());
        Iterator<String> it = names.iterator();
        while (it.hasNext()) {
            String key = it.next();
//            System.out.println(s);
            String dateStr = key.substring(key.length() - 8, key.length());
            System.out.println(key + " " + dateStr + " " + todayStr + " " + dateStr.compareTo(todayStr));

            if (dateStr.compareTo(todayStr) >= 0) {//比今天晚的日期不清空
                continue;
            }

            jedis.del(key);
        }
    }
}
