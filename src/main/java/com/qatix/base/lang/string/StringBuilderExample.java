package com.qatix.base.lang.string;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/29 5:32 PM
 */
public class StringBuilderExample {
    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder(12);
        System.out.println(sb.toString());

        System.out.println("hashCode:");
        System.out.println(sb.hashCode());

        sb.append("abc");
        sb.append("123");
        System.out.println("before setlen cap:" + sb.capacity());
        sb.setLength(23);
        System.out.println("after setlen cap:" + sb.capacity());
        sb.append("yui");
        sb.append("456");
        System.out.println(sb.toString());
        System.out.println("cap:" + sb.capacity());
        sb.append("iop");
        System.out.println(sb.toString());

        System.out.println("cap:" + sb.capacity());

        System.out.println("hashCode:");
        System.out.println(sb.hashCode());
        System.out.println("chars:");
        System.out.println(sb.chars());
    }
}
