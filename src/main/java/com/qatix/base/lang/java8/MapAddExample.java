package com.qatix.base.lang.java8;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/1 11:48 AM
 */
public class MapAddExample {
    public static void main(String[] args) {

        Map<String,Integer> map = new HashMap<>();
        map.put("key1",100);

        map.putIfAbsent("key2",0);
        System.out.println(map);

        map.computeIfPresent("key1",(key,val)->val+5);
        map.computeIfPresent("key3",(key,val)->val+3);
        map.putIfAbsent("key4", 4);

        System.out.println(map);

    }
}
