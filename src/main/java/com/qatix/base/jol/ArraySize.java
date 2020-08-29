package com.qatix.base.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

public class ArraySize {
    static void print(String message) {
        System.out.println(message);
        System.out.println("-------------------------");
    }

    public static void main(String[] args) {
        Object object  = new Object();
        //查看对象内部信息
        print(ClassLayout.parseInstance(object).toPrintable());
        //获取对象总大小
        print("size : " + GraphLayout.parseInstance(object).totalSize());


        object = new TestClz1(1);
        print(ClassLayout.parseInstance(object).toPrintable());
        //获取对象总大小
        print("size : " + GraphLayout.parseInstance(object).totalSize());

        object = new TestClz2(1);
        print(ClassLayout.parseInstance(object).toPrintable());
        //获取对象总大小
        print("size : " + GraphLayout.parseInstance(object).totalSize());


        object = new TestClz3(1);
        print(ClassLayout.parseInstance(object).toPrintable());
        //获取对象总大小
        print("size : " + GraphLayout.parseInstance(object).totalSize());
    }

    //size = 16
    private static class TestClz1{
        private int val;

        public TestClz1(int val) {
            this.val = val;
        }
    }

    //size = 32
    private static class TestClz2{
        private Integer val;

        public TestClz2(int val) {
            this.val = val;
        }
    }

    //size = 40
    private static class TestClz3{
        private byte[] bytes;

        public TestClz3(int size) {
            this.bytes = new byte[size];
        }
    }
}
