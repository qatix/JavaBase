package com.qatix.base.lang.java8;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/1 10:27 AM
 */
public class PredicateExample {
    public static void main(String[] args) {

        Predicate<String> predicate = (s) -> s.length() > 0;
        System.out.println(predicate.test("foo"));
        System.out.println(predicate.negate().test("foo"));

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        System.out.println(nonNull.test(true));
        System.out.println(isEmpty.test(""));
        System.out.println(isNotEmpty.test("abc"));
    }
}
