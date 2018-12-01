package com.qatix.base.lang.map;

import java.util.Hashtable;
import java.util.Map;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/29 6:38 PM
 */
public class HashTable {
    public static void main(String[] args) {
        //默认capacity = 11, loadFactor = 0.75
        Map<String, String> table = new Hashtable<>();
        table.put("abc", "a11");
        table.put("bcd", "324");
//        table.put("yui",null); java.lang.NullPointerException
        System.out.println(table.toString());

    }
}
