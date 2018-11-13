package com.qatix.base.spring.dependon;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/13 10:15 PM
 * https://www.boraji.com/spring-dependson-annotation-example
 */
public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BeanOne bean=context.getBean(BeanOne.class);
        bean.doSomthing();
        context.close();
    }
}
//BeanTwo Initialized
//BeanThree Initialized
//BeanOne Initialized
//Inside doSomthing() method of BeanOne
//Inside doSomthing() method of BeanTwo
//Inside doSomthing() method of BeanThree