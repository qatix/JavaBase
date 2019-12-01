package com.qatix.base.lang.statiz;

/**
 * @Author: Logan.Tang
 * @Date: 2018/10/29 6:45 PM
 */

public class SClz {
    public static final String ABC = "ABC";
    public static InnerClz innerClz = new InnerClz();

    static {
        System.out.println("static block code");
    }

    private String name;

    public SClz(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    static class InnerClz {
        public InnerClz() {
            System.out.println("Inner Clz constructed");
        }

        @Override
        public String toString() {
            return "InnerClz";
        }
    }
}
