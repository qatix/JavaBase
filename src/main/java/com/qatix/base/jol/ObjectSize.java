package com.qatix.base.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

public class ObjectSize {
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


        object = new TestClz2((byte) 3);
        print(ClassLayout.parseInstance(object).toPrintable());
        //获取对象总大小
        print("size : " + GraphLayout.parseInstance(object).totalSize());


        object = new TestClz3('y');
        print(ClassLayout.parseInstance(object).toPrintable());
        //获取对象总大小
        print("size : " + GraphLayout.parseInstance(object).totalSize());


        object = new TestClz4(11,'k');
        print(ClassLayout.parseInstance(object).toPrintable());
        //获取对象总大小
        print("size : " + GraphLayout.parseInstance(object).totalSize());


        object = new TestClz5((byte) 34,'k');
        print(ClassLayout.parseInstance(object).toPrintable());
        //获取对象总大小
        print("size : " + GraphLayout.parseInstance(object).totalSize());


        object = new TestClz6((byte) 34,1233L);
        print(ClassLayout.parseInstance(object).toPrintable());
        //获取对象总大小
        print("size : " + GraphLayout.parseInstance(object).totalSize());


        object = new TestClz7((byte) 34,1233L);
        print(ClassLayout.parseInstance(object).toPrintable());
        //获取对象总大小
        print("size : " + GraphLayout.parseInstance(object).totalSize());
    }

    private static class TestClz1{
        private int val;

        public TestClz1(int val) {
            this.val = val;
        }
    }

    private static class TestClz2{
        private byte val;

        public TestClz2(byte val) {
            this.val = val;
        }
    }

    private static class TestClz3{
        private char val;

        public TestClz3(char val) {
            this.val = val;
        }
    }

    private static class TestClz4{
        private int ival;
        private char cval;

        public TestClz4(int ival, char cval) {
            this.ival = ival;
            this.cval = cval;
        }
    }

    private static class TestClz5{
        private byte bval;
        private char cval;

        public TestClz5(byte bval, char cval) {
            this.bval = bval;
            this.cval = cval;
        }
    }

    private static class TestClz6{
        private byte bval;
        private long lval;

        public TestClz6(byte bval, long lval) {
            this.bval = bval;
            this.lval = lval;
        }
    }

    private static class TestClz7{
        private byte bval;
        private Long lval;

        public TestClz7(byte bval, long lval) {
            this.bval = bval;
            this.lval = lval;
        }
    }
}
