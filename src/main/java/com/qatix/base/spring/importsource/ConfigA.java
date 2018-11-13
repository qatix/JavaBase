package com.qatix.base.spring.importsource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/13 9:59 PM
 */
@Configuration
public class ConfigA {
    @Bean
    public BeanA getBeanA(){
        return new BeanA();
    }
}
