package com.qatix.base.spring.dependon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/13 10:15 PM
 */
@Configuration
public class AppConfig {

    @Bean("beanOne")
    @DependsOn(value = {"beanTwo", "beanThree"})
    public BeanOne getBeanOne() {
        return new BeanOne();
    }

    @Bean("beanTwo")
    public BeanTwo getBeanTwo() {
        return new BeanTwo();
    }

    @Bean("beanThree")
    public BeanThree getBeanThree() {
        return new BeanThree();
    }
}
