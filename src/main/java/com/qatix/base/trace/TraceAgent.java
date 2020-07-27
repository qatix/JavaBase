package com.qatix.base.trace;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

public class TraceAgent {
    public static void premain(String agentArgs, Instrumentation instrumentation) {
        ClassFileTransformer transformer = new TlTransformer();
        instrumentation.addTransformer(transformer, true);
    }
}
