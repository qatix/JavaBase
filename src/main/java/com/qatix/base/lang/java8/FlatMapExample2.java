package com.qatix.base.lang.java8;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/3 9:27 AM
 */
public class FlatMapExample2 {
    public static void main(String[] args) {
        Person person1 = new Person("Mike", Arrays.asList("Java", "Scala"));
        Person person2 = new Person("Devid", Arrays.asList("Php", "JavaScript"));
        Person person3 = new Person("Mohit", Arrays.asList("C++", "C#"));

        List<Person> persons = Arrays.asList(person1, person2, person3);

        System.out.println("--------Using map() method to print name of all person---------");
        persons.stream()                    // return Stream<Person>
                .map(p -> p.getName())        // return Stream<String>
                .forEach(System.out::println);

        System.out.println("\n--------Using flatMap() method to print all languages--------");
        persons.stream()                    // return Stream<Person>
                .map(p -> p.getLanguages())   // return Stream<List<String>>
                .flatMap(l -> l.stream())     // return Stream<String>
                .forEach(System.out::println);
    }

    @Data
    private static class Person {
        private String name;
        private List<String> languages;

        public Person(String name, List<String> languages) {
            this.name = name;
            this.languages = languages;
        }
    }
}
