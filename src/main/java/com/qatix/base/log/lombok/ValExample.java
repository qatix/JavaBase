package com.qatix.base.log.lombok;

import lombok.val;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Logan.Tang
 * @Date: 2018/10/29 6:12 PM
 */
public class ValExample {
    private static void test1() {
        final HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(0, "zero");
        map.put(5, "five");
        for (final Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
        }
//        map = new HashMap<>();Error:(18, 9) java: cannot assign a value to final variable map
    }

    private static void test2() {
        val map = new HashMap<Integer, String>();
        map.put(0, "zero");
        map.put(5, "five");
        for (val entry : map.entrySet()) {
            System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
        }
//        map =  new HashMap<Integer, String>();Error:(29, 9) java: cannot assign a value to final variable map
    }

    public static void main(String[] args) {
        test1();
        test2();
    }
}
