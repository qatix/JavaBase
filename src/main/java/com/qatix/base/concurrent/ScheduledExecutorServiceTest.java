package com.qatix.base.concurrent;

import java.util.concurrent.*;

public class ScheduledExecutorServiceTest {

    public static void main(String[] args) {

       Thread thread = new Thread(new Runnable() {
            public void run() {
                int sec = 0;
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("interupted exit");
                        break;
                    }
                    System.out.println("sec:" + sec++);
                }
            }

        });
       thread.start();
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);
        ScheduledFuture<String> scheduledFuture = ses.schedule(new Callable<String>() {
            public String call() throws Exception {
                System.out.println("executed");
                return "scheduled result";
            }
        }, 5, TimeUnit.SECONDS);

        try {
            System.out.println("result:" + scheduledFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        /**
         * Just like an ExecutorService, the ScheduledExecutorService
         * needs to be shut down when you are finished using it.
         * If not, it will keep the JVM running, even when all other threads have been shut down.
         */
        ses.shutdown();
        thread.interrupt();
    }
}
