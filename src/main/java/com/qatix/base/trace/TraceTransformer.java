package com.qatix.base.trace;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.NotFoundException;

import java.io.IOException;

public interface TraceTransformer {
    boolean needTransform(String className);

    void doTransform(CtClass var1) throws NotFoundException, CannotCompileException, IOException;
}