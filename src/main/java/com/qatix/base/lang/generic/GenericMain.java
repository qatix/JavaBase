package com.qatix.base.lang.generic;

/**
 * 泛型方法在什么时候使用呢？
 *
 * ①类型约束只是在局部
 * 泛型的作用于不是针对全局，而仅仅是针对局部的时候，如 GenericObject 类
 *
 * ②静态方法需要处理泛型
 * 如上面例子中的ifThenElse,普通的静态方法是不能使用泛型的。
 */
public class GenericMain {
    public static void main(String[] args) {
        double res = new GenericObject<Double>().ifThenElse(1 > 2, 1.0, 2.0);
        System.out.println(res);

        double res2 = new GenericMethod().ifThenElseNonStatic(1 > 2, 1.0, 2.0);
        System.out.println(res2);

        double res3 = GenericMethod.ifThenElse(3 > 2, 1.0, 2.0);
        System.out.println(res3);
    }
}
