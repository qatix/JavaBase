package com.qatix.base.lang.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/6 3:01 PM
 */
public class FinalMap {
    //不推荐使用
    //Because Double Brace Initialization (DBI) creates an anonymous class with a reference to the instance of the owning object, its use can lead to memory leaks if the anonymous inner class is returned and held by other objects. Even when there's no leak, DBI is so obscure that it's bound to confuse most maintainers.
    private static final Map<String, String> primitives = new HashMap<String, String>() {
        {
            this.put("int", "I");
            this.put("boolean", "Z");
            this.put("byte", "B");
            this.put("char", "C");
            this.put("short", "S");
            this.put("float", "F");
            this.put("long", "J");
            this.put("double", "D");
        }
    };

    public static void main(String[] args) {
        System.out.println(primitives.toString());
        primitives.put("abc", "AAA");
        System.out.println(primitives.toString());
        System.out.println(primitives.remove("byte"));
        System.out.println(primitives.toString());

//        primitives = new HashMap<>();//Error:(31, 9) java: cannot assign a value to final variable primitives
//        primitives.put("C11","I");
    }
}

//