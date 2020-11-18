package com.qatix.base.lang.string;

public class Character {
    public static void main(String[] args) {
        String str = "hello world";
        for(int i=0;i<str.length();i++){
            System.out.println(str.charAt(i));
        }
        for(char ch:str.toCharArray()){
            System.out.println(ch);
        }
    }
}
