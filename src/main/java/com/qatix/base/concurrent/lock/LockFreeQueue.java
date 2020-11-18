package com.qatix.base.concurrent.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;


/**
 * 用数组实现无锁有界队列
 *
 * 1、什么是无锁(Lock-Free)编程
 *
 * 当谈及 Lock-Free 编程时，我们常将其概念与 Mutex(互斥) 或 Lock(锁) 联系在一起，描述要在编程中尽量少使用这些锁结构，降低线程间互相阻塞的机会，以提高应用程序的性能。类同的概念还有 "Lockless" 和 "Non-Blocking" 等。实际上，这样的描述只涵盖了 Lock-Free编程的一部分内容。本质上说，Lock-Free 编程仅描述了代码所表述的性质，而没有限定或要求代码该如何编写。
 *
 * 基本上，如果程序中的某一部分符合下面的条件判定描述，则我们称这部分程序是符合 Lock-Free的。反过来说，如果某一部分程序不符合下面的条件描述，则称这部分程序是不符合 Lock-Free 的。
 *
 * @see https://www.cnblogs.com/linlinismine/p/9263426.html
 */

public class LockFreeQueue {

    private AtomicReferenceArray<Integer> atomicReferenceArray;
    //代表为空，没有元素
    private static final  Integer EMPTY = null;
    //头指针,尾指针
    AtomicInteger head,tail;


    public LockFreeQueue(int size){
        atomicReferenceArray = new AtomicReferenceArray<>(new Integer[size + 1]);
        head = new AtomicInteger(0);
        tail = new AtomicInteger(0);
    }

    /**
     * 入队
     * @param element
     * @return
     */
    public boolean add(Integer element){
        int index = (tail.get() + 1) % atomicReferenceArray.length();
        if( index == head.get() % atomicReferenceArray.length()){
            System.out.println("当前队列已满,"+ element+"无法入队!");
            return false;
        }
        while(!atomicReferenceArray.compareAndSet(index,EMPTY,element)){
            return add(element);
        }
        tail.incrementAndGet(); //移动尾指针
        System.out.println("入队成功!" + element);
        return true;
    }

    /**
     * 出队
     * @return
     */
    public Integer poll(){
        if(head.get() == tail.get()){
            System.out.println("当前队列为空");
            return null;
        }
        int index = (head.get() + 1) % atomicReferenceArray.length();
        Integer ele = (Integer) atomicReferenceArray.get(index);
        if(ele == null){ //有可能其它线程也在出队
            return poll();
        }
        while(!atomicReferenceArray.compareAndSet(index,ele,EMPTY)){
            return poll();
        }
        head.incrementAndGet();
        System.out.println("出队成功!" + ele);
        return ele;
    }

    public void print(){
        StringBuffer buffer = new StringBuffer("[");
        for(int i = 0; i < atomicReferenceArray.length() ; i++){
            if(i == head.get() || atomicReferenceArray.get(i) == null){
                continue;
            }
            buffer.append(atomicReferenceArray.get(i) + ",");
        }
        buffer.deleteCharAt(buffer.length() - 1);
        buffer.append("]");
        System.out.println("队列内容:"    +buffer.toString());

    }

}