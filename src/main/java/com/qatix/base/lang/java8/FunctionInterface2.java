package com.qatix.base.lang.java8;

import java.util.function.Function;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/1 10:31 AM
 */
public class FunctionInterface2 {
    public static void main(String[] args) {

        //Function 接口有一个参数并且返回一个结果，并附带了一些可以和其他函数组合的默认方法（compose, andThen）
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        System.out.println(backToString.apply("123"));

    }
}
