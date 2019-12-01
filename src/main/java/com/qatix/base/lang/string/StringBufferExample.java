package com.qatix.base.lang.string;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/3 8:36 PM
 */
public class StringBufferExample {
    public static void main(String[] args) {
        final StringBuffer sb = new StringBuffer().append("1");
        sb.append("2"); //会继续执行，final只修饰变量地址不可变
        System.out.println(sb);
    }
}
