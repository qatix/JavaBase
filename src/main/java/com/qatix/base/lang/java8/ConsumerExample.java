package com.qatix.base.lang.java8;

import java.util.function.Consumer;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/1 10:36 AM
 */
public class ConsumerExample {
    public static void main(String[] args) {
        Consumer<Student> consumer = (s) -> System.out.println("Hello," + s.getName());
        consumer.accept(new Student("Feng"));
    }

    private static class Student {
        private String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
