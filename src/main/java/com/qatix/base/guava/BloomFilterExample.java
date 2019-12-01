package com.qatix.base.guava;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;
import org.apache.commons.lang3.RandomUtils;

/**
 * @Author: Logan.Tang
 * @Date: 2018/10/30 11:35 AM
 */
public class BloomFilterExample {


    public static void main(String[] args) {
        Funnel<String> nameFunnel = new Funnel<String>() {
            @Override
            public void funnel(String from, PrimitiveSink into) {
                into.putString(from, Charsets.UTF_8);
            }
        };

        //1%的错误率，500个可以错5个
        BloomFilter<String> bloomFilter = BloomFilter.create(nameFunnel, 500, 0.01);
        int MAX_COUNT = 1000;
        String[] userArr = new String[15];
        for (int i = 0; i < MAX_COUNT; i++) {
            String tuser = String.format("user-%d", RandomUtils.nextInt(0, 800));
            bloomFilter.put(tuser);
            if (i % 100 == 99) {
                userArr[i / 100] = tuser;
            }
        }

        userArr[10] = "user-not-exist1";
        userArr[11] = "user-not-exist2";
        userArr[12] = "user-not-exist3";
        userArr[13] = "user-not-exist4";
        userArr[14] = "user-not-exist5";
        for (String user : userArr) {
            if (bloomFilter.mightContain(user)) {
                System.out.println("contain:" + user);
            } else {
                System.out.println("not-contain:" + user);
            }
        }
    }
}

/*
* contain:user-269
contain:user-25
contain:user-513
contain:user-606
contain:user-775
contain:user-473
contain:user-660
contain:user-737
contain:user-400
contain:user-254
not-contain:user-not-exist1
contain:user-not-exist2  误报
not-contain:user-not-exist3
not-contain:user-not-exist4
not-contain:user-not-exist5
* */