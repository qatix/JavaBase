package com.qatix.base.lang.expression;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/8 12:57 PM
 */
public class UseEngine {
    public static void main(String[] args) throws ScriptException {

        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("js");
        Object result = engine.eval("4*4 + 3*(5-2) + 6/2");
        System.out.println(result);
    }
}
