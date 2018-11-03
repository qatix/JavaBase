package com.qatix.base.lang.statiz;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/3 8:30 PM
 */
public class Static2 {
    private static Static2 instance = new Static2();
    private static int count = 2;

    private Static2(){
        System.out.println("construction");
        System.out.println(count);
        //输出0，因为count=2还未执行
    }

    public static Static2 getInstance(){
        System.out.println("getInstance");
        return instance;
    }

    public static void main(String[] args) {
        Static2.getInstance();
    }
}
