package com.qatix.base.concurrent.deadlock;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * 误用线程池可能导致死锁
 * http://www.importnew.com/30277.html
 */
public class ThreadPoolExample {

    /**
     * 没有死锁
     */
    private static void test1NoDeadlock() {
        ExecutorService pool = newFixedThreadPool(10);
        pool.submit(() -> {
            System.out.println("first");
            try {
                pool.submit(() -> System.out.println("second")).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println("third");
        });
    }

    private static void test2Deadlock() {
        ExecutorService pool = newFixedThreadPool(1);
        pool.submit(() -> {
            System.out.println("first");
            try {
                //.get()会阻塞线程，但是又拿不到线程
                pool.submit(() -> System.out.println("second")).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println("third");
        });
    }

    private static void test3NoDeadlock() {
        ExecutorService pool = newFixedThreadPool(1);
        pool.submit(() -> {
            System.out.println("first");
            //.get()会阻塞线程，但是又拿不到线程
            pool.submit(() -> System.out.println("second"));
            System.out.println("third");
        });
    }

    public static void main(String[] args) {
//        test1NoDeadlock();
//        test2Deadlock();
        test3NoDeadlock();
    }
}
