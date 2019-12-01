package com.qatix.base.concurrent;

import java.util.concurrent.Exchanger;

public class ExchangerTest {

    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();
        ExchangerRunnable exchangerRunnable1 = new ExchangerRunnable(exchanger, new ExObjec("tang", 30));
        ExchangerRunnable exchangerRunnable2 = new ExchangerRunnable(exchanger, new ExObjec("rong", 25));
        new Thread(exchangerRunnable1).start();
        new Thread(exchangerRunnable2).start();
        /**
         * Thread-1 exchange ExObjec{name='rong', age=25} for ExObjec{name='tang', age=30}
         Thread-0 exchange ExObjec{name='tang', age=30} for ExObjec{name='rong', age=25}
         */
    }
}

class ExObjec {
    private String name;
    private int age;

    public ExObjec(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "ExObjec{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class ExchangerRunnable implements Runnable {
    Exchanger exchanger;
    Object object;

    public ExchangerRunnable(Exchanger exchanger, Object object) {
        this.exchanger = exchanger;
        this.object = object;
    }

    @Override
    public void run() {
        try {
            Object previous = this.object;
            this.object = this.exchanger.exchange(this.object);
            System.out.println(Thread.currentThread().getName() + " exchange " + previous + " for " + this.object);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
