package com.qatix.base.lang.reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintClassFieldExample {
    public static void main(String[] args) {
        System.out.println("Student:");
        printClassFields(Student.class);

        System.out.println("=================");
        System.out.println("HighSchoolStudent:");
        printClassFields(HighSchoolStudent.class);
    }

    private static void printClassFields(Class clz) {
        System.out.println("DeclaredFields:");
        Field[] fields = clz.getDeclaredFields();//获取所有字段,public和protected和private,但是不包括父类字段
        for (Field field : fields) {
            System.out.println("The field is: " + field.getName());
        }


        System.out.println("Fields:");//获取所有public字段,包括父类字段
        fields = clz.getFields();
        for (Field field : fields) {
            System.out.println("The field is: " + field.getName());
        }


        //需要递归获取父类的字段
        System.out.println("All Fields:");
        List<Field> fieldList = new ArrayList<>();
        Class tempClass = clz;
        while (tempClass != null && !tempClass.getName().equalsIgnoreCase("java.lang.Object")) {
            System.out.println("clz name:" + tempClass.getName());
            fieldList.addAll(Arrays.asList(clz.getDeclaredFields()));
            tempClass = tempClass.getSuperclass();
        }

        for (Field field : fieldList) {
            System.out.println("The field is: " + field.getName());
        }
    }

    static class Student {
        public String publicFieldInStudent;
        protected String protectedFieldInStudent;
        private String name;
        private String claz;
        private Integer age;
    }

    static class HighSchoolStudent extends Student {
        public String publicFieldInChild;
        protected String protectedFieldInChild;
        private String learnType;
        private String highSchoolName;
    }
}

//output:
//The field is: name
//The field is: claz
//The field is: age
//HighSchoolStudent:
//The field is: learnType
//The field is: highSchoolName