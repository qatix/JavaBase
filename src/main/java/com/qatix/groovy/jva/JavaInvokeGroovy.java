package com.qatix.groovy.jva;

import com.google.gson.Gson;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import groovy.util.GroovyScriptEngine;

import java.lang.reflect.Method;

public class JavaInvokeGroovy {

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        fun1();
//        fun2();
//        fun3();
        System.out.print(System.currentTimeMillis() - time);
        System.out.println(" ms");
    }

    private static void fun3() {
        try {
            GroovyScriptEngine engine = new GroovyScriptEngine("");
            Binding binding = new Binding();
            binding.setVariable("language", "Groovy");
            Object value = engine.run("SimpleScript.groovy", binding);
            assert value.equals("The End");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fun2() {
        Binding binding = new Binding();
        binding.setVariable("x", 10);
        binding.setVariable("language", "Groovy");

        GroovyShell shell = new GroovyShell(binding);
        Object value = shell.evaluate("println \"Welcome to $language\"; y = x * 2; z = x * 3; println x ");
        assert value.equals(10);
        assert binding.getVariable("y").equals(20);
        assert binding.getVariable("z").equals(30);
    }

    private static void fun1() {
        GroovyClassLoader loader = new GroovyClassLoader();
        try {
            String scriptText = "class Foo {\n" +
                    " String name\n" +
                    " def setName(String newName){\n" +
                    " this.name = newName \n" +
                    "}\n" +
//                    " def getName(){\n" +
//                    " return this.name \n" +
//                    "}\n" +
                    " int add(int x, int y) { x + y }}";

            Class newClazz = loader.parseClass(scriptText);

            Object obj = newClazz.newInstance();

            Object ret = obj.getClass().getMethod("add", int.class, int.class)
                    .invoke(obj, 23, 3);
            System.out.println(ret);
            System.out.println(obj.getClass().getCanonicalName());
//            System.out.println(obj.getClass().getSimpleName());
            Method method = obj.getClass().getMethod("setName", String.class);
//            method.setAccessible(true);
            method.invoke(obj, "test-new-name");
            System.out.println(obj.toString());
            Object name = obj.getClass().getMethod("getName")
                    .invoke(obj);
            System.out.println("name=" + name);

            System.out.println(new Gson().toJson(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}