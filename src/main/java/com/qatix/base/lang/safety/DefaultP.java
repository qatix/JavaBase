package com.qatix.base.lang.safety;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/1 9:48 AM
 */
public class DefaultP {
    public static void main(String[] args) {
        Student student = new Student("zhang",23,"Beijing");
        System.out.println(student.name);
        System.out.println(student.address);
        System.out.println(student.getAge());
    }
}
