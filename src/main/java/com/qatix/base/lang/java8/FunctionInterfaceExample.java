package com.qatix.base.lang.java8;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/1 10:04 AM
 */
interface Convertor<F, T> {
    T convert(F from);
}

public class FunctionInterfaceExample {
    public static void main(String[] args) {

        Convertor<String, Double> convertor = new Convertor<String, Double>() {
            @Override
            public Double convert(String from) {
                return Double.valueOf(from);
            }
        };

        double converted = convertor.convert("45.44");
        System.out.println(converted);

        Convertor<String, Integer> convertor1 = Integer::valueOf;
        int converted2 = convertor1.convert("47");
        System.out.println(converted2);
    }
}
