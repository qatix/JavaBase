package com.qatix.groovy

class InnerClass {
    class A {
        static class B {
            String name = "Bclass";
        }
    }

    public static void main(String[] args) {
        A.B b = new A.B();
        println(b.name)
    }
}
