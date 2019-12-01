package com.qatix.base.lang.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchExample {

    public static void main(String[] args) {
        System.out.println(match("^\\d+$", "12312"));
        System.out.println(match("^\\d+$", "12312a"));
        System.out.println(match("^[A-Za-z]+$", "12312"));
        System.out.println(match("^[A-Za-z]+$", "aaa"));
        System.out.println(match("^[A-Za-z]+$", "12312bbb"));
        System.out.println(match("[!@#\\$%]*", "aa%#"));
        System.out.println(match("[!@#\\$%]+", "12-"));
        System.out.println(match("[!@#\\$%]+", "#$"));

    }

    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
