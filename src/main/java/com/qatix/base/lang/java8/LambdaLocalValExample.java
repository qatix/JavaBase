package com.qatix.base.lang.java8;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/1 10:23 AM
 */
public class LambdaLocalValExample {
    public static void main(String[] args) {
        final int num = 1;
        Convertor<Integer,String> convertor = (from)->String.valueOf(from+num);
        System.out.println(convertor.convert(2));

        int num2 = 1;//隐式转换
        Convertor<Integer,String> convertor2 = (from)->String.valueOf(from+num2);
        System.out.println(convertor2.convert(2));
//        num2 = 3;报错：Error:(14, 76) java: local variables referenced from a lambda expression must be final or effectively final
    }
}
