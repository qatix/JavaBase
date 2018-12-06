package com.qatix.base.lang.java8;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/1 9:54 AM
 */
public class FormulaTest {

    public static void main(String[] args) {

        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return a+a;
            }
        };

        System.out.println(formula.sqrt(2));
        System.out.println(formula.calculate(4));
    }
}
