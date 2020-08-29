package com.qatix.base.lang.string;

import java.util.StringJoiner;

public class StringSplitJoin {
    public static void main(String[] args) {
        String domain = "abc.123.com";
        String[] parts = domain.split("\\.");
        StringJoiner sj = new StringJoiner(".");

        for(int i= parts.length-1;i>=0;i--){
            sj.add(parts[i]);
            System.out.println(sj.toString());
        }

        StringBuilder sb = new StringBuilder();
        for(int i= parts.length-1;i>=0;i--){
            sb.append(parts[i]);
            if(i>0){
                sb.append(".");
            }
            System.out.println(sb.toString());
        }
    }
}
