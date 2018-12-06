package com.qatix.base.lang.safety;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/1 9:47 AM
 */
public class Student {
    String name; //default protected
    private int age;
    protected  String address;

    public Student(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public int getAge() {
        return age;
    }
}
