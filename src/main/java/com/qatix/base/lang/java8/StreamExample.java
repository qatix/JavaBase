package com.qatix.base.lang.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/1 10:42 AM
 */
public class StreamExample {
    public static void main(String[] args) {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        System.out.println("output a:");
        stringCollection.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);

        System.out.println("sort b:");
        stringCollection.stream().sorted().filter((s) -> s.startsWith("b")).forEach(System.out::println);

        System.out.println("self define sort b:");
        stringCollection.stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .filter((s) -> s.startsWith("B"))
                .forEach(System.out::println);


        boolean anyStartsWithA = stringCollection.stream()
                .anyMatch((s) -> s.startsWith("a"));
        System.out.println(anyStartsWithA);

        boolean allStartsWithA = stringCollection.stream()
                .allMatch((s) -> s.startsWith("a"));
        System.out.println(allStartsWithA);

        boolean noneStartsWithZ = stringCollection.stream()
                .noneMatch((s) -> s.startsWith("z"));
        System.out.println(noneStartsWithZ);


        long startsWithBCount = stringCollection.stream()
                .filter((s) -> s.startsWith("b"))
                .count();
        System.out.println(startsWithBCount);


        //reduce
        Optional<String> reduced = stringCollection.stream()
                .sorted()
                .reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);
    }
}
