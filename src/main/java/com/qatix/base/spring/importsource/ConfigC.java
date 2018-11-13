package com.qatix.base.spring.importsource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/13 10:00 PM
 */
@Configuration
@Import(value = {ConfigA.class})
@ImportResource(locations = {
//        "classpath:/com/kika/terminal/learning/importsource/ConfigB.xml"
        "classpath:spring/configB.xml"
})
public class ConfigC {
    @Bean
    public BeanC getBeanC(){
        return new BeanC();
    }
}
