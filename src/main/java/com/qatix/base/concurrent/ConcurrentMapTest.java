package com.qatix.base.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ConcurrentMapTest {
    public static void main(String[] args) {

        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("abc", 1);
        map.put("bcd", 32);
        System.out.println(map);

        System.out.println(map.get("abc"));


        map.computeIfPresent("abc", new BiFunction<String, Integer, Integer>() {
            @Override
            public Integer apply(String s, Integer integer) {
                return integer*integer;
            }
        });
        System.out.println(map);

        map.computeIfAbsent("jkl", new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return 100;
            }
        });
        System.out.println(map);

    }
}
