package com.qatix.base.designpattern.singleton;

public class StaticSingletonTest {
    public static void main(String[] args) {
        StaticSingleton s1 = StaticSingleton.getInstance();
        System.out.println(s1.hashCode());

        StaticSingleton s2 = StaticSingleton.getInstance();
        System.out.println(s2.hashCode());

    }
}

class StaticSingleton {

    private StaticSingleton() {
        System.out.println("StaticSingleton is create");
    }

    public static StaticSingleton getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static StaticSingleton instance = new StaticSingleton();
    }
}
