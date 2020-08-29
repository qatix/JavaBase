package com.qatix.base.lang.generic;

public class GenericObject<T> {
    public T ifThenElse(boolean condition, T t1, T t2) {
        if (condition) {
            return t1;
        }
        return t2;
    }
}
