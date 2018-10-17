package com.qatix.base.concurrent;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InvoteAny {

    private static void testFunc() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Set<Callable<String>> callables = new HashSet<Callable<String>>();

        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "task 1";
            }
        });

        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "task 2";
            }
        });

        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "task 3";
            }
        });

        try {
            String result = executorService.invokeAny(callables);
            System.out.println("result=" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }

    public static void main(String[] args) {
        testFunc();
        testFunc();
        testFunc();
        testFunc();
        testFunc();
    }
}
