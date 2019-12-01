package com.qatix.base.log.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: Logan.Tang
 * @Date: 2018/10/29 6:05 PM
 */
public class NonNullExample {

    private static String getPersonKey(Person person) {
        String key = person.name + "|" + person.getCountry();
        return key;
    }

    public static void main(String[] args) {
        Person person1 = new Person("zhang", "cn");
        System.out.println(getPersonKey(person1));

        Person person2 = null;
        System.out.println(getPersonKey(person2));
    }

    @Data
    @AllArgsConstructor
    static class Person {
        private String name;
        private String country;
    }
}

/*
before:
Exception in thread "main" java.lang.NullPointerException
	at com.qatix.base.log.lombok.NonNullExample$Person.access$000(NonNullExample.java:16)
	at com.qatix.base.log.lombok.NonNullExample.getPersonKey(NonNullExample.java:22)
	at com.qatix.base.log.lombok.NonNullExample.main(NonNullExample.java:31)

after:
Exception in thread "main" java.lang.NullPointerException: person is marked @NonNull but is null
	at com.qatix.base.log.lombok.NonNullExample.getPersonKey(NonNullExample.java:21)
	at com.qatix.base.log.lombok.NonNullExample.main(NonNullExample.java:31)
* */