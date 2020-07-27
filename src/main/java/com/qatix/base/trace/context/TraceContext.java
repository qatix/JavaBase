package com.qatix.base.trace.context;

public class TraceContext {
    private static final ThreadLocal<Object> CONTEXT = new ThreadLocal<>();
    public static Object getContext() {
        return CONTEXT.get();
    }
    public static void setContext(Object obj) {
        CONTEXT.set(obj);
    }
    public static void removeContext() {
        CONTEXT.remove();
    }

}