package com.qatix.base.lang.onlinecompile;

public class MyClassLoader extends ClassLoader {

    public MyClassLoader() {
        super(MyClassLoader.class.getClassLoader());
    }

    Class<?> getTestClass(byte[] classBytes) {
        return defineClass(null, classBytes, 0, classBytes.length);
    }
}
