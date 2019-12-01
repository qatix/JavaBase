package com.qatix.base.concurrent.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {

        DelayQueue queue = new DelayQueue();
        Delayed e1 = new DelayedElement(3, "1sec-3");
        Delayed e2 = new DelayedElement(2, "2sec-2");
        Delayed e3 = new DelayedElement(5, "3sec-5");
        Delayed e4 = new DelayedElement(1, "4sec-1");

        queue.put(e1);
        queue.put(e2);
        queue.put(e3);
        queue.put(e4);
        System.out.println("put done");

        while (true) {
            System.out.println("start to get");
            System.out.println(queue.take());
        }
    }
}

class DelayedElement implements Delayed {
    private long delayTime;
    private String name;

    public DelayedElement(long delayTime, String name) {
        this.delayTime = delayTime;
        this.name = name;
    }

    public long getDelay(TimeUnit unit) {
        return this.delayTime;
    }

    public int compareTo(Delayed o) {
        DelayedElement o1 = (DelayedElement) o;
        if (this.delayTime > o1.delayTime) {
            return 1;
        } else if (this.delayTime < o1.delayTime) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "DelayedElement{" +
                "delayTime=" + delayTime +
                ", name='" + name + '\'' +
                '}';
    }
}