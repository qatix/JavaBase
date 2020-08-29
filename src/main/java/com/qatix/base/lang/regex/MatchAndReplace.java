package com.qatix.base.lang.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchAndReplace {
    private final static Pattern DIGIT_PATTERN = Pattern.compile("[^0-9]");

    public static void main(String[] args) {
        String a = "love23next234csdn3423javaeye";
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(a);
        System.out.println(m.replaceAll("").trim());
        //output:232343423

        List<String> digitList = new ArrayList<String>();
        //[Mandatory] When using regex, precompile needs to be done in order to increase the matching performance.
        //Note: Do not define Pattern pattern = Pattern.compile(.); within method body.
//        Pattern p2 = Pattern.compile("[^0-9]"); //不建议
        Matcher m2 = DIGIT_PATTERN.matcher(a);
        String result = m2.replaceAll("");
        for (int i = 0; i < result.length(); i++) {
            digitList.add(result.substring(i, i + 1));

        }
        System.out.println(digitList);
    }

}
