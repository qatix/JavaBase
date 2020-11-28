package com.qatix.base.thread;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * https://github.com/alibaba/transmittable-thread-local
 * 在使用线程池等会池化复用线程的组件情况下，提供ThreadLocal值的传递功能，解决异步执行时上下文传递的问题。
 * 一个Java标准库本应为框架/中间件设施开发提供的标配能力，本库功能聚焦 & 0依赖，支持Java 12/11/10/9/8/7/6。
 * <p>
 * JDK的InheritableThreadLocal类可以完成父线程到子线程的值传递。但对于使用线程池等会池化复用线程的组件的情况，
 * 线程由线程池创建好，并且线程是池化起来反复使用的；这时父子线程关系的ThreadLocal值传递已经没有意义，
 * 应用需要的实际上是把 任务提交给线程池时的ThreadLocal值传递到 任务执行时。
 */
public class TransmittableThreadLocalExample {
    public static void main(String[] args) throws InterruptedException {
        TransmittableThreadLocal<String> parent = new TransmittableThreadLocal<String>();
        parent.set("value-set-in-parent");

        ThreadLocal<String> parentTl = new ThreadLocal<>();
        parentTl.set("java-thread-local");

        ThreadLocal<String> parentTli = new InheritableThreadLocal<>();
        parentTli.set("java-thread-local-inherit");


        Runnable task = () -> {
            System.out.println("Tl:" + Thread.currentThread().getName() + ":" + parentTl.get());
            System.out.println("Tli:" + Thread.currentThread().getName() + ":" + parentTli.get());
            System.out.println("Ttl:" + Thread.currentThread().getName() + ":" + parent.get());
        };
        // 额外的处理，生成修饰了的对象ttlRunnable
        Runnable ttlRunnable = TtlRunnable.get(task);

        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            es.submit(ttlRunnable);
        }

        es.awaitTermination(5, TimeUnit.SECONDS);
        es.shutdown();
    }
}
