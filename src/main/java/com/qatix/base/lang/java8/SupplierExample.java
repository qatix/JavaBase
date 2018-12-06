package com.qatix.base.lang.java8;

import java.util.function.Supplier;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/1 10:33 AM
 */
public class SupplierExample {
    private static class Student{
        private String name;
        public Student(){
            name = "Name is not set";
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
    public static void main(String[] args) {
        //Supplier接口返回一个任意泛型的值，没有任何参数
        Supplier<Student> supplier =  Student::new;
        Student student = supplier.get();
        System.out.println(student);

        student.setName("zhang");
        System.out.println(student);
    }
}
