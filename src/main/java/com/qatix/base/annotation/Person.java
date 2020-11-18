package com.qatix.base.annotation;

import lombok.Data;

@Data
public class Person {
    @MyAnno(value = "testAnno")
    private String stra;
    private String strb;
    private String strc;

    public Person(String str1,String str2,String str3){
        super();
        this.stra = str1;
        this.strb = str2;
        this.strc = str3;
    }
}
