package com.qatix.base.lang.reflection;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/1 9:32 AM
 */
public class GetClassExample {

    private static class Student{
        private String firstName;
        private String lastName;
        private String grade;
        private int age;

        public Student() {
        }

        public Student(String firstName, String lastName, String grade, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.grade = grade;
            this.age = age;
        }
    }

    private static void test1() throws ClassNotFoundException {
        System.out.println("test1:");
        String className = "com.qatix.base.lang.reflection.GetClassExample$Student";
        Class clazz = Class.forName(className);
        System.out.println(clazz);
    }

    private static void test2(){
        System.out.println("test2:");
        Class clazz1 = Student.class;
        Class clazz2 = Student.class;
        System.out.println(clazz1);
        System.out.println("clazz1==clazz2:" + (clazz1 == clazz2)); //true
    }

    private static void test3(){
        System.out.println("test3:");
        Student student = new Student();
        Class clazz1 = student.getClass();
        System.out.println(clazz1);

        Student student2 = new Student();
        Class clazz2 = student2.getClass();

        System.out.println("new clazz1==clazz2:" + (clazz1 == clazz2)); //true

    }

    public static void main(String[] args) throws ClassNotFoundException {
        test1();
        test2();
        test3();
    }
}
