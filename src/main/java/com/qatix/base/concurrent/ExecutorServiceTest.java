package com.qatix.base.concurrent;

import java.util.concurrent.*;

public class ExecutorServiceTest {

        public static final ExecutorService es = Executors.newFixedThreadPool(3);
//    public static final ExecutorService es = Executors.newCachedThreadPool();
//    public static final ExecutorService es = Executors.newSingleThreadExecutor();

    private void testExecute() {
        es.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("testExecute executed");
            }
        });
    }

    private void testSubmitRunable() throws ExecutionException, InterruptedException {
        Future future = es.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("test runnable");
            }
        });
        System.out.println("runnale result:" + future.get());
    }

    private void testSubmitCallable() throws ExecutionException, InterruptedException {
        final Future future = es.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("aysnchronous called");
                return "callable test";
            }
        });
        System.out.println("callable result:" + future.get());
    }

    public static void main(String[] args) {
        ExecutorServiceTest est = new ExecutorServiceTest();
        try {
            est.testSubmitRunable();
            est.testSubmitCallable();
            est.testExecute();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
