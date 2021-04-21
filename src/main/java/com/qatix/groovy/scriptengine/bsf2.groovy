package com.qatix.groovy.scriptengine

import org.apache.bsf.BSFManager

BSFManager manager = new BSFManager();
Vector<String> ignoreParamNames = null;
Vector<Integer> args = new Vector<>();
args.add(2);
args.add(5);
args.add(1);
Integer actual = (Integer) manager.apply("groovy", "applyTest", 0, 0,
        "def summer = { a, b, c -> a * 100 + b * 10 + c }", ignoreParamNames, args);
println(actual.intValue())
assertEquals(251, actual.intValue());
