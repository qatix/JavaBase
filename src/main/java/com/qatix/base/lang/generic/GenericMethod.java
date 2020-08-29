package com.qatix.base.lang.generic;

public class GenericMethod {

    /**
     * <T> 必须在 修饰词 (public private static final等)和返回值之间
     *
     * @param condition
     * @param t1
     * @param t2
     * @param <T>
     * @return
     */
    public static <T> T ifThenElse(boolean condition, T t1, T t2) {
        if (condition) {
            return t1;
        }
        return t2;
    }

    public <T> T ifThenElseNonStatic(boolean condition, T t1, T t2) {
        if (condition) {
            return t1;
        }
        return t2;
    }
}
