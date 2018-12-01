package com.qatix.base.lang.java8;

import java.util.Optional;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/1 10:41 AM
 */
public class OptionalExample {
    public static void main(String[] args) {
        Optional<String> optional = Optional.of("CK");
        System.out.println(optional.isPresent());
        System.out.println(optional.get());
        System.out.println(optional.orElse("fallback"));

        optional.ifPresent((s) -> System.out.println(s.charAt(1)));
    }
}
