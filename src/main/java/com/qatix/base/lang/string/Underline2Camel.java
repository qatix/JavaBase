package com.qatix.base.lang.string;

import com.google.common.base.CaseFormat;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/2 11:07 AM
 */
public class Underline2Camel {
    public static void main(String[] args) {
        String name = "myFirstName";

        //my_first_name
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name));

        String tstr = "my_last_name";
        //MyLastName
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tstr));

        //myLastName
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tstr));

        //my-last-name
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, tstr));

        //MY_LAST_NAME
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_UNDERSCORE, tstr));
    }
}
