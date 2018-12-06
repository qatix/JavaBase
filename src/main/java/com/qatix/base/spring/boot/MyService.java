package com.qatix.base.spring.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/6 2:49 PM
 */
@Slf4j
@Service
public class MyService implements InitializingBean {

    public MyService() {
        log.info("construct invoke");
    }

    private void func1(){
        log.info("func1 invoke");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
      log.info("afterPropertiesSet invoke");
      func1();
    }
}
