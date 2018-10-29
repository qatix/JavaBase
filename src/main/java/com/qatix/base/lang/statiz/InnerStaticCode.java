package com.qatix.base.lang.statiz;

/**
 * @Author: Logan.Tang
 * @Date: 2018/10/29 6:43 PM
 */
public class InnerStaticCode {
    private static String str = "123";
    static {
        System.out.println(str);
        str = "456";
        System.out.println(str);
    }
    public static void main(String[] args) {
        System.out.println("main:");
        System.out.println(str);
    }
}
