package com.qatix.base.trace;

import javassist.*;

import java.io.IOException;

public class ForkJoinPoolTransformer implements TraceTransformer {
    private static final String FORK_JOIN_TASK_CLASS_NAME = "java.util.concurrent.ForkJoinTask";

    @Override
    public boolean needTransform(String className) {
        return FORK_JOIN_TASK_CLASS_NAME.equals(className);
    }

    @Override
    public void doTransform(CtClass clazz) throws NotFoundException, CannotCompileException, IOException {
        String className = clazz.getName();
        //添加context$field$add$by$trace字段,初始值为TraceContext.getContext(),这样就获取了调用线程的上下文
        CtField contextField = CtField.make("private final java.lang.Object context$field$add$by$trace;", clazz);
        clazz.addField(contextField, "com.qatix.base.trace.context.TraceContext.getContext();");
        System.out.println("add new field context$field$add$by$trace to class " + className);
        CtMethod doExecMethod = clazz.getDeclaredMethod("doExec");
        CtMethod newDoExecMethod = CtNewMethod.copy(doExecMethod, "doExec", clazz, (ClassMap)null);
        doExecMethod.setName("original$doExec$method$renamed$by$trace");
        doExecMethod.setModifiers(doExecMethod.getModifiers() & -2 | 2);
        //java.lang.Object backup = com.qatix.base.trace.context.TraceContextUtil.backupAndSet(this.context$field$add$by$trace);
        //try {
        //   return original$doExec$method$renamed$by$trace($$);
        //} finally {
        //    TraceContextUtil.restoreBackup(backup);
        //}
        newDoExecMethod.setBody("{\njava.lang.Object backup = com.qatix.base.trace.context.TraceContextUtil.backupAndSet(context$field$add$by$trace);\ntry {\n    return original$doExec$method$renamed$by$trace($$);\n} finally {\n    com.ezlippi.trace.agent.context.TraceContextUtil.restoreBackup(backup);\n}\n}");
        clazz.addMethod(newDoExecMethod);
        System.out.println("insert code around method " + doExecMethod + " of class " + className);


    }
}
