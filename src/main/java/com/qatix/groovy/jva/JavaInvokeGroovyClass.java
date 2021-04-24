package com.qatix.groovy.jva;

import com.google.gson.Gson;
import com.sun.codemodel.internal.JDefinedClass;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import groovy.util.GroovyScriptEngine;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Arrays;

public class JavaInvokeGroovyClass {

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        fun1();
        System.out.print(System.currentTimeMillis() - time);
        System.out.println(" ms");
    }

    private static void fun1() {
        GroovyClassLoader loader = new GroovyClassLoader();
        try {
            String scriptTextUser = "package com.qatix.groovy.jva\n" +
                    "class User2 {\n" +
                    "    String name;\n" +
                    "    int age;\n" +
                    "    int gender;\n" +
                    "    String dob;\n" +
                    "    String job;\n" +
                    "    Address2 address;\n" +
                    "}";

            String scriptTextAddress = "package com.qatix.groovy.jva\n" +
                    "class Address2 {\n" +
                    "    String country;\n" +
                    "    String province;\n" +
                    "    String city;\n" +
                    "    String street;\n" +
                    "}";


            Class addressClazz = loader.parseClass(scriptTextAddress);
            Class userClazz = loader.parseClass(scriptTextUser);
            loader.loadClass("com.qatix.groovy.jva.User2",true,true);
            loader.loadClass("com.qatix.groovy.jva.Address2",true,true);

            Object obj = userClazz.newInstance();
            obj.getClass().getMethod("setName", String.class).invoke(obj,"lisi");

            System.out.println("getCanonicalName:");
            System.out.println(userClazz.getCanonicalName());

//            JavaInvokeGroovyClass.class.getClassLoader().loadClass("com.qatix.groovy.jva.User2");
            System.out.println(new Gson().toJson(obj));
            Yaml yaml = new Yaml();
            Object gUser = yaml.load(new FileInputStream(
                            new File("src/main/java/com/qatix/groovy/jva/user.yaml"))
            );
            System.out.println(gUser);
            System.out.println(new Gson().toJson(gUser));

            String jsonStr = new Gson().toJson(gUser);
            Object gUser2 = new Gson().fromJson(jsonStr,userClazz);
            System.out.println(gUser2);
            System.out.println(new Gson().toJson(gUser2));

            userClazz.getMethod("setJob", String.class).invoke(gUser2,"Teacher");
            System.out.println(new Gson().toJson(gUser2));

//            java.lang.NoSuchMethodException
//            userClazz.getMethod("setInterest", String.class).invoke(gUser2,"Basketball");
//            System.out.println(new Gson().toJson(gUser2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}