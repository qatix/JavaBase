package com.qatix.base.spring.dependon;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/13 10:14 PM
 */
public class BeanTwo {

    public BeanTwo() {
        System.out.println("BeanTwo Initialized");
    }

    public void doSomthing() {
        System.out.println("Inside doSomthing() method of BeanTwo");
    }

}
