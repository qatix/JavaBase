package com.qatix.base.jvm.oom;

/**
 * vm args:-Xss=128k
 */
public class VMStackSOF {
    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        VMStackSOF vmStackSOF = new VMStackSOF();
        try{
            vmStackSOF.stackLeak();
        }catch (Exception e){
            System.out.println("stack lenth:" + vmStackSOF.stackLength);
//            e.printStackTrace();
            throw e;
        }
    }
}

//    Exception in thread "main" java.lang.StackOverflowError
//        at oom.VMStackSOF.stackLeak(VMStackSOF.java:8)
//        at oom.VMStackSOF.stackLeak(VMStackSOF.java:8)
//        at oom.VMStackSOF.stackLeak(VMStackSOF.java:8)....
