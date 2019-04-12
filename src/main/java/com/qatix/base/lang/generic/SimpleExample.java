package com.qatix.base.lang.generic;

public class SimpleExample {
    public static void main(String[] args) {
        Box<Integer> intV = new Box<>(111);
        System.out.println(intV.getClass().getName());
        System.out.println(intV.getData());
//        Box<Number> numV = intV; compile error
        Box<Float> floatV = new Box<>(3.22f);
//        numV.setData(floatV); compile error
        System.out.println(floatV.getClass().getName());
        System.out.println(floatV.getData());

        printData(intV);
        printData(floatV);

    }

    //这里必须是<?>
    //类型通配符一般是使用 ? 代替具体的类型实参
    private static void printData(Box<?> data) {
        System.out.println("data:" + data.getData());
    }
}

//output
//com.qatix.base.lang.generic.Box
//111
//com.qatix.base.lang.generic.Box
//3.22