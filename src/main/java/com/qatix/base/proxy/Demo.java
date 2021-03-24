package com.qatix.base.proxy;

import java.lang.reflect.Proxy;

public class Demo {
    public static void main(String[] args) {
        ImplementInterface ii = new ImplementInterface();

        Speak speak = (Speak) Proxy.newProxyInstance(Speak.class.getClassLoader(),
                new Class[]{Speak.class}, ii);

        speak.say();

        System.out.println();

        speak.dosomething();
    }
}
