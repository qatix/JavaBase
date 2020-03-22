package com.qatix.base.lang.clz;

public class TestClass {
    static {
        System.out.println("static code block exexcuted");
    }

    public TestClass() {
        System.out.println("TestClass construct method invoked");
    }

    public static void staticFunc() {
        System.out.println("staicFunc executed..");
    }

    public void testMethod() {
        System.out.println(this.toString() + ":TestMethod invoked");
    }
}
