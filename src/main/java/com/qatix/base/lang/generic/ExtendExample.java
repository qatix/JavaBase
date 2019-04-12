package com.qatix.base.lang.generic;

public class ExtendExample {
    public static void main(String[] args) {
        Box<Integer> intV = new Box<>(111);
        Box<Float> floatV = new Box<>(3.22f);
        Box<String> stringV = new Box<>("Hello");

        printData1(intV);
        printData1(floatV);
//        printData1(stringV); compile error


        printData2(stringV);
    }

    private static void printData1(Box<? extends Number> data) {
        System.out.println("number data:" + data.getData());
    }

    private static void printData2(Box<?> data) {
        System.out.println("generic data:" + data.getData());
    }
}
