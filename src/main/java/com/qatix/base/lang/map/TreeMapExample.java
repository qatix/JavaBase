package com.qatix.base.lang.map;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/13 10:35 PM
 */
public class TreeMapExample {
    public static void main(String[] args) {
        Map<String,String> map = new TreeMap<>(Collections.reverseOrder());
        map.put("aaa","122");
        map.put("ccc","2121");
        map.put("bbb","cdsd");
        map.put("cc1","cc1");

        System.out.println(map.toString());
    }
}
