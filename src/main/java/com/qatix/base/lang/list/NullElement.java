package com.qatix.base.lang.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/3 8:34 PM
 */
public class NullElement {
    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        list.add(null);
        list.add(100L);
        System.out.println("size:" + list.size());
        long sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i); //空指针一次
            //Exception in thread "main" java.lang.NullPointerException
        }
        System.out.println(sum);
    }
}
