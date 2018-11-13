package com.qatix.base.spring.importsource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/13 10:02 PM
 * https://www.boraji.com/spring-4-import-and-importresource-example
 */
public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ConfigC.class);

        BeanA beanA=context.getBean(BeanA.class);
        beanA.doSomething();

        BeanB beanB=context.getBean(BeanB.class);
        beanB.doSomething();

        BeanC beanC=context.getBean(BeanC.class);
        beanC.doSomething();

        context.close();
    }
}
//Inside doSomething() method of BeanA
//Inside doSomething() method of BeanB
//Inside doSomething() method of BeanC
