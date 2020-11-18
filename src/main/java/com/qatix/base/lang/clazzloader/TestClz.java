package com.qatix.base.lang.clazzloader;

public class TestClz {
    public void say() {
        System.out.println("Hello World V1");
    }

//    public void say() {
//        System.out.println("Hello World V2");
//    }

    public static void main(String[] args) {
        new TestClz().say();
    }
}
