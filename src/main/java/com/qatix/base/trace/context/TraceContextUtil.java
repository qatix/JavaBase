package com.qatix.base.trace.context;

public class TraceContextUtil {
    //设置调用线程的上下文到当前执行线程中,并返回执行线程之前的上下文
    public static Object backupAndSet(Object currentContext) {
        Object backupContext = TraceContext.getContext();
        TraceContext.setContext(currentContext);
        return backupContext;
    }
    //恢复执行线程的上下文
    public static void restoreBackup(Object backup) {
        TraceContext.setContext(backup);
    }
}