package com.qatix.base.lang.array;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ArrayEqualExample {

    @Data
    @AllArgsConstructor
    private static class Student {
        private String name;
        private int age;
    }

    public static void main(String[] args) {
        Student student1 = new Student("zahngs", 31);
        Student student2 = new Student("lisi", 22);
        Student student3 = new Student("lisi", 22);
        List<Student> aList = new ArrayList<>();
        List<Student> bList = new ArrayList<>();
        aList.add(student1);
        aList.add(student2);

        bList.add(student3);
        bList.add(student1);
        System.out.println(aList);
        System.out.println(bList);
        System.out.println("is-equal:" + CollectionUtils.isEqualCollection(aList, bList));
    }
}
