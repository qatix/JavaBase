package com.qatix.base.lang.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/11 11:54 AM
 */
public class ExtractExample {

    public static void main(String[] args) {
        String desStr = "{02=房源序号, 13=序号, 231=合同编号, 32=项目名称}";
        String regex = "\\d+=([^(,+})]*)";
        Pattern mPattern = Pattern.compile(regex);
        Matcher mMatcher = mPattern.matcher(desStr);
        while (mMatcher.find()) {
            System.out.println(mMatcher.group(0) + "||" + mMatcher.group(1));
        }
    }
}
