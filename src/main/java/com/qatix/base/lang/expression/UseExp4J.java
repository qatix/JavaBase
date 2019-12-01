package com.qatix.base.lang.expression;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.junit.Assert;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/8 1:03 PM
 * @see https://www.objecthunter.net/exp4j/#Evaluating_an_expression
 */
public class UseExp4J {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Expression e = new ExpressionBuilder("3 * sin(y) - 2 / (x - 2)")
                .variables("x", "y")
                .build()
                .setVariable("x", 2.3)
                .setVariable("y", 3.14);
        double result = e.evaluate();
        System.out.println(result);


        //同步计算
        Expression e2 = new ExpressionBuilder("x+2*y-z*z")
                .variables("x", "y", "z")
                .build()
                .setVariable("x", 10)
                .setVariable("y", 12)
                .setVariable("z", 3);
        System.out.println(e2.evaluate());


        //异步计算
        Expression e3 = new ExpressionBuilder("sqrt(x) + y*y")
                .variables("x", "y")
                .build()
                .setVariable("x", 10)
                .setVariable("y", 5);
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Double> future = e3.evaluateAsync(es);
        System.out.println(future.get());

//        Using implicit muliplication
        double result2 = new ExpressionBuilder("2cos(xy)")
                .variables("x", "y")
                .build()
                .setVariable("x", 0.5d)
                .setVariable("y", 0.25d)
                .evaluate();
        Assert.assertEquals(2d * Math.cos(0.5d * 0.25d), result2, 0d);
        System.out.println("equal:" + result2);

//        Use constants in an expression
        String expr = "pi+π+e+φ";
        double expected = 2 * Math.PI + Math.E + 1.61803398874d;
        Expression e4 = new ExpressionBuilder(expr).build();
        Assert.assertEquals(expected, e4.evaluate(), 0d);
        System.out.println("equal:" + expr + ":" + result2);

//        Use scientific notation in an expression
        String expr2 = "7.2973525698e-7";
        double expected2 = Double.parseDouble(expr2);
        Expression e5 = new ExpressionBuilder(expr).build();
        Assert.assertEquals(expected2, e5.evaluate(), 0d);
        System.out.println("equal:" + expr2 + ":" + result2);//not equal
    }
}
