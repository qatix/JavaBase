package com.qatix.base.log.lombok;

import lombok.Synchronized;

/**
 * https://projectlombok.org/features/Synchronized
 * @Author: Logan.Tang
 * @Date: 2018/10/29 6:31 PM
 */
public class SynchronizedExample {
    private final Object readLock = new Object();

    @Synchronized
    public static void hello(){
        System.out.println("hello");
    }

    @Synchronized
    public int answerToLife(){
        return 44;
    }

    @Synchronized("readLock")
    public void foo(){
        System.out.println("bar");
    }

    public static void main(String[] args) {
        hello();
        SynchronizedExample se = new SynchronizedExample();
        System.out.println(se.answerToLife());
        se.foo();
    }
}

/*
java
* public class SynchronizedExample {
  private static final Object $LOCK = new Object[0];
  private final Object $lock = new Object[0];
  private final Object readLock = new Object();

  public static void hello() {
    synchronized($LOCK) {
      System.out.println("world");
    }
  }

  public int answerToLife() {
    synchronized($lock) {
      return 42;
    }
  }

  public void foo() {
    synchronized(readLock) {
      System.out.println("bar");
    }
  }
}
* */