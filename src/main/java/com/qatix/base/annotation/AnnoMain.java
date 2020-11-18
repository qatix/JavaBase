package com.qatix.base.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnoMain {
    public static void main(String[] args) {
        Person person = new Person("无注解", "无注解", "无注解");

        System.out.println("Before:" + person.toString());
        doAnnoTest(person);

        System.out.println("After:" + person.toString());
    }

    private static void doAnnoTest(Object obj) {
        Class clazz = obj.getClass();
        Field[] declareFields = clazz.getDeclaredFields();
        for (Field field : declareFields) {
            MyAnno myAnno = field.getAnnotation(MyAnno.class);
            if (myAnno != null) {
                String fieldName = field.getName();
                try {
                    Method setMethod = clazz.getDeclaredMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), String.class);
                    String annoValue = myAnno.value();
                    setMethod.invoke(obj, annoValue);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
