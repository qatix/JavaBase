package com.qatix.base.lang.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MapPutExample {
    public static void main(String[] args) {

        Map<Integer, Integer> amap = new HashMap<>();
        int[] nums = new int[]{1, 2, 3, 4, 2, 3, 7};
        for (int num : nums) {
            amap.putIfAbsent(num, 11);
        }


        amap.computeIfPresent(7, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                System.out.println("present- i1:"+integer+"|i2:"+integer2);
                return integer*integer2;
            }
        });
        System.out.println(amap);

        amap.computeIfAbsent(8, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                System.out.println("absent- i1:"+integer);
                return integer*integer;
            }
        });
        System.out.println(amap);
    }
}
