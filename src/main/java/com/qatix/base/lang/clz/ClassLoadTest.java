package com.qatix.base.lang.clz;

/**
 * 类加载有三种方式：
 * 1、命令行启动应用时候由JVM初始化加载
 * 2、通过Class.forName()方法动态加载
 * 3、通过ClassLoader.loadClass()方法动态加载
 */
public class ClassLoadTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //method1
        TestClass tc0 = new TestClass();
        tc0.testMethod();

        //method 2
        Class clz1 = Class.forName("com.qatix.base.lang.clz.TestClass");
        if (clz1 != null) {
            TestClass tc1 = (TestClass) clz1.newInstance();
            tc1.testMethod();
        } else {
            System.out.println("clz1 not found");
        }

        //method 3
        ClassLoader classLoader = ClassLoadTest.class.getClassLoader();
        System.out.println(classLoader);
        Class clz0 = classLoader.loadClass("com.qatix.base.lang.clz.TestClass");
        if (clz0 != null) {
            TestClass tc1 = (TestClass) clz0.newInstance();
            tc1.testMethod();
        } else {
            System.out.println("clz0 not found");
        }
    }
}
