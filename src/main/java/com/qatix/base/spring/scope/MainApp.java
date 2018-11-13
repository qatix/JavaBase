package com.qatix.base.spring.scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/13 9:35 PM
 */
public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        AccountService service1 = context.getBean("accountService", AccountService.class);
        service1.setAccountHolder("BORAJI");
        System.out.println(service1.getAccountHolder());

        AccountService service2 = context.getBean("accountService", AccountService.class);
        System.out.println(service2.getAccountHolder());
//        service2.setAccountHolder("Bill");
//        System.out.println(service2.getAccountHolder());


        ProductService service3 = context.getBean("productService", ProductService.class);
        service3.setProductHolder("iPhone");
        System.out.println(service3.getProductHolder());

        ProductService service4 = context.getBean("productService", ProductService.class);
        System.out.println(service4.getProductHolder());


        ProductService service5 = context.getBean("productService2", ProductService.class);
        service5.setProductHolder("iPhoneX");
        System.out.println(service5.getProductHolder());

        ProductService service6 = context.getBean("productService2", ProductService.class);
        System.out.println(service6.getProductHolder());

        context.close();
    }
}
