package com.qatix.base.guava;

import com.google.common.base.Optional;

public class OptionalTest {
    public static void main(String[] args) {
        Integer invalidInput = null;
        //Optional.of - 返回要用作参数Optional类的实例。检查传递的值是否为null。
        Optional<Integer> a = Optional.of(invalidInput);
        Optional<Integer> b = Optional.of(new Integer(20));
        System.out.println(sum(a,b));
    }

    public static int sum(Optional<Integer> a,Optional<Integer> b){
        return a.get() + b.get();
    }
}
