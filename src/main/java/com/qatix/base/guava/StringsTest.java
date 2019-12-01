package com.qatix.base.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.Arrays;
import java.util.Iterator;

public class StringsTest {

    private static void testJoiner() {
        Joiner joiner = Joiner.on(";").skipNulls();
        String str = joiner.join("aaa", null, "bbb", "122");
        System.out.println(str);

        String str2 = Joiner.on(",").join(Arrays.asList(1, 5, 7));
        System.out.println(str2);
    }

    public static void testSplitter() {
        Iterable<String> its = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("foo,bar,,   qux");
        Iterator<String> it = its.iterator();
        while (it.hasNext()) {
            System.out.println("split:" + it.next());
        }
    }

//    public static void testChars(){
//        String noControl = CharMatcher.javaIsoControl().removeFrom(string); // remove control characters
//        String theDigits = CharMatcher.digit().retainFrom(string); // only the digits
//        String spaced = CharMatcher.whitespace().trimAndCollapseFrom(string, ' ');
//        // trim whitespace at ends, and replace/collapse whitespace into single spaces
//        String noDigits = CharMatcher.javaDigit().replaceFrom(string, "*"); // star out all digits
//        String lowerAndDigit = CharMatcher.javaDigit().or(CharMatcher.javaLowerCase()).retainFrom(string);
//        // eliminate all characters that aren't digits or lowercase
//    }

    public static void main(String[] args) {
        testJoiner();
        testSplitter();
    }
}
