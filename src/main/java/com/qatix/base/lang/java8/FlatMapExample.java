package com.qatix.base.lang.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/3 9:26 AM
 */
public class FlatMapExample {
    public static void main(String[] args) {
        System.out.println("-----------------Using map-----------------");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream().map(n -> n * 2)          // return Stream<Integer>
                .forEach(System.out::println);

        System.out.println("-----------------Using flatMap-----------------");
        List<Integer> numbers1 = Arrays.asList(1, 2);
        List<Integer> numbers2 = Arrays.asList(4, 5);
        List<Integer> numbers3 = Arrays.asList(7, 8);

        Stream.of(numbers1, numbers2, numbers3)    // return Stream<List<Integer>>
                .flatMap(list -> list.stream())      // return Stream<Integer>
                .map(n -> n * 2)                     // return Stream<Integer>
                .forEach(System.out::println);
    }
}
