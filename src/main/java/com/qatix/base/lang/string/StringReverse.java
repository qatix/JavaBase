package com.qatix.base.lang.string;

public class StringReverse {
    public static void main(String[] args) {
        String str = "hello";
        System.out.println(str);
        System.out.println(reserve(str));

    }

    public static  String reserve(String str){
        if(str == null){
            return null;
        }
        int len = str.length();
        char[] chars = str.toCharArray();
        for(int i=0;i<len/2;i++){
            char tmp = chars[i];
            chars[i] = chars[len-1-i];
            chars[len-1-i] = tmp;
        }
        return String.valueOf(chars);
    }
}
