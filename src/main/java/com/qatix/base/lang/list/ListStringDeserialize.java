package com.qatix.base.lang.list;

public class ListStringDeserialize {
    public static void main(String[] args) {
        String str = "[null, 1, null, null]";
        System.out.println(str.substring(1,str.length()-1));
        String[] arr = str.substring(1,str.length()-1).split(",");
        for (String s:arr){
            s = s.trim();
            System.out.println("["+s+"]");
            if(!"null".equals(s)){
                System.out.println(Integer.parseInt(s.trim()));
            }
        }
    }
}
