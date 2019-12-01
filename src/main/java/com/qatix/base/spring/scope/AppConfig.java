package com.qatix.base.spring.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/13 9:34 PM
 */
@Configuration
public class AppConfig {
    @Bean("accountService")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public AccountService getAccountService() {
        return new AccountService();
    }


    @Bean("productService")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ProductService getProductService() {
        return new ProductService();
    }

    /**
     * 默认是SINGLETON
     *
     * @return
     */
    @Bean("productService2")
    public ProductService getProductService2() {
        return new ProductService();
    }
}