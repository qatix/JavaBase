package com.qatix.base.lang.java8;

import java.util.function.Supplier;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/1 10:33 AM
 */
public class SupplierExample {
    public static void main(String[] args) {
        //Supplier接口返回一个任意泛型的值，没有任何参数
        Supplier<Student> supplier = Student::new;
        System.out.println("supplier initialized");
        Student student = supplier.get();
        System.out.println("supplier get");
        System.out.println(student);

        student.setName("zhang");
        System.out.println(student);
    }

    private static class Student {
        private String name;

        public Student() {
            name = "Name is not set";
            System.out.println("construct");
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
