package com.qatix.base.trace;

import javassist.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ThreadPoolTransformer implements TraceTransformer {
    private static final String TRACE_RUNNABLE_CLASS_NAME = TraceRunnable.class.getName();
    private static final String TRACE_CALLABLE_CLASS_NAME = TraceCallable.class.getName();

    private static final Set<String> TO_TRANSFORM_METHODS = new HashSet<>();

    static {
        TO_TRANSFORM_METHODS.add("execute");
        TO_TRANSFORM_METHODS.add("submit");
        TO_TRANSFORM_METHODS.add("schedule");
        TO_TRANSFORM_METHODS.add("scheduleAtFixedRate");
        TO_TRANSFORM_METHODS.add("scheduleWithFixedDelay");
    }

    @Override
    public boolean needTransform(String className) {
        return "java.util.concurrent.ThreadPoolExecutor".equals(className)
                || "java.util.concurrent.ScheduledThreadPoolExecutor".equals(className);
    }

    @Override
    public void doTransform(CtClass clazz) throws NotFoundException, CannotCompileException, IOException {
        CtMethod[] methods = clazz.getDeclaredMethods();
        int length = methods.length;

        for(int i = 0; i < length; ++i) {
            CtMethod method = methods[i];
            transformMethod(clazz, method);
        }
    }

    static void transformMethod(CtClass clazz, CtMethod method) throws NotFoundException, CannotCompileException {
        if (TO_TRANSFORM_METHODS.contains(method.getName())) {
            if (method.getDeclaringClass() == clazz) {
                int modifiers = method.getModifiers();
                if (Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers)) {
                    CtClass[] parameterTypes = method.getParameterTypes();
                    StringBuilder insertCode = new StringBuilder();

                    for(int i = 0; i < parameterTypes.length; ++i) {
                        CtClass parameterType = parameterTypes[i];
                        String code;
                        if ("java.lang.Runnable".equals(parameterType.getName())) {
                            //$1 = TraceRunnable.get($1)返回TraceRunnable实例
                            code = String.format("$%d = %s.get($%d);", i + 1, TRACE_RUNNABLE_CLASS_NAME, i + 1);
                            System.out.println("insert code before method " + method + " of class " + method.getDeclaringClass().getName() + ": " + code);
                            insertCode.append(code);
                        } else if ("java.util.concurrent.Callable".equals(parameterType.getName())) {
                            //$1 = TraceCallable.get($1)返回TraceCallable实例
                            code = String.format("$%d = %s.get($%d);", i + 1, TRACE_CALLABLE_CLASS_NAME, i + 1);
                            System.out.println("insert code before method " + method + " of class " + method.getDeclaringClass().getName() + ": " + code);
                            insertCode.append(code);
                        }
                    }

                    if (insertCode.length() > 0) {
                        method.insertBefore(insertCode.toString());
                    }

                }
            }
        }
    }
}
