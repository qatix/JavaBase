package com.qatix.base.designpattern;

public class SimpleSingletonTest {
    public static void main(String[] args) {
        SimpleSingleton s1 = SimpleSingleton.getInstance();
        System.out.println(s1);
        System.out.println(s1.hashCode());


        SimpleSingleton s2 = SimpleSingleton.getInstance();
        System.out.println(s2);
        System.out.println(s2.hashCode());
    }
}

class SimpleSingleton {

    private static SimpleSingleton instance = null;

    private SimpleSingleton() {
        System.out.println("LazySingleton is create");
    }

    public static synchronized SimpleSingleton getInstance() {
        if (null == instance) {
            instance = new SimpleSingleton();
        }
        return instance;
    }

}