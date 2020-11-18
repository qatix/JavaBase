package com.qatix.base.lang.stack;

import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.toString());
        System.out.println(stack.peek());
        while (!stack.empty()){
            System.out.println(stack.pop());
        }

    }
}
