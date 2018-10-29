package com.qatix.base.lang.enums;

public class Test {

    public static void main(String[] args) {

        ColorEnum ce = ColorEnum.RED;
        System.out.println(ce);
        System.out.println(ce.name());

        for (ColorEnum c: ColorEnum.values()
             ) {
            System.out.println(c);
        }


        String typeName =  "f5";
        TypeEnum typeEnum = TypeEnum.fromName(typeName);
        if(TypeEnum.BALANCE == typeEnum){
            System.out.println("equal");
        }else{
            System.out.println("not equal");
        }

        TypeEnum t = TypeEnum.valueOf("firewall");
        System.out.println(t);
    }
}
