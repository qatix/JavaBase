package com.qatix.base.lang.java8;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/1 9:53 AM
 */
public interface Formula {
    double calculate(int a);

    default double sqrt(int a){
        return Math.sqrt(a);
    }
}
