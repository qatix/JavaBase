package com.qatix.base.lang.java8.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/1 11:54 AM
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Hints.class)
public @interface Hint {
    String value();
}
