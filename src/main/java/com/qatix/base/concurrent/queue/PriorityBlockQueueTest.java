package com.qatix.base.concurrent.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockQueueTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new PriorityBlockingQueue<Element>(100);
        queue.put(new Element("tang", 11));
        queue.put(new Element("zhang", 4));
        queue.put(new Element("chen", 4));
        queue.put(new Element("huang", 71));
        queue.put(new Element("liu", 3));

        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
    }
}

class Element implements Comparable {
    private String name;
    private int sort;

    public Element(String name, int sort) {
        this.name = name;
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "Element{" +
                "name='" + name + '\'' +
                ", sort=" + sort +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Element e = (Element) o;
        if (e.sort == this.sort) {
            return 0;
        }

        return this.sort > e.sort ? 1 : -1;
    }
}
