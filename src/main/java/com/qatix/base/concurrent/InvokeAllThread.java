package com.qatix.base.concurrent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 1.exec.invokeAll(tasks)
 * 2.exec.invokeAll(tasks, timeout, unit)
 * 两种方法都是阻塞方法
 * <p>
 * invoteAll(tasks,timeout,unit) 批量提交任务，如果到设定时间未完成，则任务被取消
 * <p>
 * invokeAll将Future添加到返回的容器中，这样可以使用任务容器的迭代器，从而调用者可以将它表现的Callable与Future 关联起来。
 * 当所有任务都完成时、调用线程被中断时或者超过时限时，限时版本的invokeAll都会返回结果。 超过时限后，任务尚未完成的任务都会被取消。
 */
public class InvokeAllThread {

    static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        InvokeAllThread it = new InvokeAllThread();
        try {
            it.getRankedTravelQuotes();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("is shutdown?:" + executorService.isShutdown());
        }
    }

    private void getRankedTravelQuotes() throws InterruptedException {
        List<QuoteTask> tasks = new ArrayList<QuoteTask>();
        Random r = new Random();
        for (int i = 1; i <= 20; i++) {
            tasks.add(new QuoteTask(r.nextInt(100), i));
        }

        //限定超时时间5秒
//        List<Future<BigDecimal>> futures = executorService.invokeAll(tasks,5, TimeUnit.SECONDS);
        List<Future<BigDecimal>> futures = executorService.invokeAll(tasks);

        List<BigDecimal> totalPriceList = new ArrayList<BigDecimal>();

        Iterator<QuoteTask> taskIter = tasks.iterator();

        for (Future<BigDecimal> future : futures) {
            QuoteTask task = taskIter.next();
            try {
                totalPriceList.add(future.get());
            } catch (ExecutionException e) {
                totalPriceList.add(BigDecimal.valueOf(-1));
                System.out.println("task execute exception:" + task);
            } catch (CancellationException e) {
                totalPriceList.add(BigDecimal.ZERO);
                System.out.println("task timeout canceled:" + task);
            }
        }

        for (BigDecimal bd : totalPriceList) {
            System.out.println(bd);
        }

        executorService.shutdown();
    }

    private class QuoteTask implements Callable<BigDecimal> {
        public final double price;
        public final int num;

        private QuoteTask(double price, int num) {
            this.price = price;
            this.num = num;
        }

        public BigDecimal call() throws Exception {
            Random r = new Random();
            long time = (r.nextInt(10) + 1) * 100;
            Thread.sleep(time);

            BigDecimal d = BigDecimal.valueOf(price * num).setScale(2);
            System.out.println("timecost:" + time / 1000 + "s,price=" + price + ",num=" + num + ",sum=" + d);
            return d;
        }

        @Override
        public String toString() {
            return "QuoteTask{" +
                    "price=" + price +
                    ", num=" + num +
                    '}';
        }
    }
}
