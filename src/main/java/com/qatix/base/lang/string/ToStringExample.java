package com.qatix.base.lang.string;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/29 5:39 PM
 */
public class ToStringExample {
    public static void main(String[] args) {
        Student student = new Student("Zhang", "CN", 21);
        System.out.println(student.toString());
    }

    private static class Student {
        private String name;
        private String country;
        private int age;

        public Student(String name, String country, int age) {
            this.name = name;
            this.country = country;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public String getCountry() {
            return country;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", country='" + country + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
