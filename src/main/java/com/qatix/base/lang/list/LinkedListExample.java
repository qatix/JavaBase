package com.qatix.base.lang.list;

import java.util.LinkedList;
import java.util.List;

public class LinkedListExample {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        int cap = 10;
        int i = 1;
        while(i<8){
            linkedList.add(i++);
        }
        System.out.println(linkedList.size());

       Integer last = linkedList.removeLast();
        System.out.println("last:"+last);
    }
}
