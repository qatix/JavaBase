package com.qatix.groovy

class methodtest {
    static int method(String arg) {
        return 1;
    }

    static int method(Object arg) {
        return 2;
    }

    static void main(String[] args) {
        Object o = "Object";
        int result = method(o);
        println "res:" + result
    }
}
