package com.qatix.base.lang.string;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/29 5:50 PM
 */
public class StringAddExample {

    public static void main(String[] args) {

        String s1 =  "abc";
        for(int i=0;i<=10;i++){
            s1 = s1 + "-abc";
        }
        System.out.println(s1);
    }
}
