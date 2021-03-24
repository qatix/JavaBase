package com.qatix.base.lang.java8;

import java.util.stream.IntStream;

public class IntRange {
    public static void main(String[] args) {
       int sum = IntStream.range(0,100).sum();
        System.out.println("sum="+sum);
    }
}
