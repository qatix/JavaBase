package com.qatix.base.lang.java8.annotation;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/1 11:56 AM
 */
public class Main {
    public static void main(String[] args) {
        Hint hint = Person.class.getAnnotation(Hint.class);
        System.out.println(hint); //null

        Hints hints1 = Person.class.getAnnotation(Hints.class);
        System.out.println(hints1);
        System.out.println(hints1.value().length);//3

        Hint[] hints2 = Person.class.getAnnotationsByType(Hint.class);
        System.out.println(hints2.length);//3

        System.out.println("new:");
        Hint hint2 = Person2.class.getAnnotation(Hint.class);
        System.out.println(hint2); //null
//
        Hints hints12 = Person2.class.getAnnotation(Hints.class);
        System.out.println(hints12);
        System.out.println(hints12.value().length);//2

        Hint[] hints22 = Person2.class.getAnnotationsByType(Hint.class);
        System.out.println(hints22.length);//2
    }
}
//null
//@com.qatix.base.lang.java8.annotation.Hints(value=[@com.qatix.base.lang.java8.annotation.Hint(value=hint1), @com.qatix.base.lang.java8.annotation.Hint(value=hint2), @com.qatix.base.lang.java8.annotation.Hint(value=hint3)])
//3
//3
//new:
//null
//@com.qatix.base.lang.java8.annotation.Hints(value=[@com.qatix.base.lang.java8.annotation.Hint(value=hint11), @com.qatix.base.lang.java8.annotation.Hint(value=hint12)])
//2
//2
