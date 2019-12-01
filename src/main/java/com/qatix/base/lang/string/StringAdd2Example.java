package com.qatix.base.lang.string;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/29 5:50 PM
 */
public class StringAdd2Example {

    public static void main(String[] args) {

        StringBuilder s1 = new StringBuilder("abc");
        for (int i = 0; i <= 10; i++) {
            s1.append("-abc");
        }
        System.out.println(s1.toString());
    }
}
