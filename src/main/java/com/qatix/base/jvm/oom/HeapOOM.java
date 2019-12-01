package com.qatix.base.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms20m -Xms20 -XX:+HeadDumpOnOutOfMemoryError
 * <p>
 * java oom.HeapOOM -Xms20m -Xms20 -XX:+HeadDumpOnOutOfMemoryError
 */
public class HeapOOM {
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        System.out.println("started");
        while (true) {
            list.add(new OOMObject());
        }
    }

    static class OOMObject {

    }
}


//Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
//        at java.util.Arrays.copyOf(Arrays.java:3210)
//        at java.util.Arrays.copyOf(Arrays.java:3181)
//        at java.util.ArrayList.grow(ArrayList.java:265)
//        at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:239)