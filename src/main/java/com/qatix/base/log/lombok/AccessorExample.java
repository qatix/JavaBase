package com.qatix.base.log.lombok;

public class AccessorExample {
    public static void main(String[] args) {

        AccessorsObject ao = new AccessorsObject();
        ao.setName("test").setStatus(10).setType("normal").setNo("fno11");
        System.out.println(ao.toString());

    }
}
