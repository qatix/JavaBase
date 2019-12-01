package com.qatix.base.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapTest {
    public static void main(String[] args) {

        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("abc", 1);
        map.put("bcd", 32);
        System.out.println(map);

        System.out.println(map.get("abc"));

    }
}
