package com.qatix.base.spring.dependon;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/13 10:14 PM
 */
public class BeanOne {
    @Autowired
    private BeanTwo beanTwo;

    @Autowired
    private BeanThree beanThree;

    public BeanOne() {
        System.out.println("BeanOne Initialized");
    }

    public void doSomthing() {
        System.out.println("Inside doSomthing() method of BeanOne");
        beanTwo.doSomthing();
        beanThree.doSomthing();
    }
}
