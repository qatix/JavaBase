package com.qatix.base.lang.onlinecompile;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 源码执行器，进行的操作如下:
 * 源码去除package头部
 * 获取className
 * 为生成的class指定目录
 * 编译源码，生成字节码
 * 加载字节码，反射调用execute方法
 */
public class MyCodeRuntime {
    private static final Pattern CLASS_PATTERN = Pattern.compile("class\\s+([$_a-zA-Z][$_a-zA-Z0-9]*)\\s*");
    private static final List<String> OPTIONS = new ArrayList<>(); // 编译参数
    private static final List<File> CLASSPATH = new ArrayList<>(); // classpath
    private static final String PROJECT_DIR = System.getProperty("user.dir"); // 工程目录
    private static final String TMP_DIR = "tmp/codeTest"; // 存储编译产物

    static {
        OPTIONS.add("-target");
        OPTIONS.add("1.8");
        File classRootFile = new File(PROJECT_DIR, TMP_DIR);
        if (!classRootFile.exists()) {
            classRootFile.mkdir();
        }
        //根据实际情况添加对应的环境变量，class或者jar都可以
        CLASSPATH.add(new File(classRootFile, "build/classes/main"));
    }

    /**
     * 执行代码
     *
     * @param code 源码
     * @return 返回结果
     * @throws IOException io异常
     */
    public static String run(String code) throws IOException {
        if (code == null || code.length() == 0) {
            return "代码为空";
        }
        code = code.trim();
        //去除package
        if (code.startsWith("package")) {
            int index = code.indexOf("\n");
            if (index != -1) {
                code = code.substring(index + 1);
            }
        }

        //找出入口类名
        Matcher matcher = CLASS_PATTERN.matcher(code);
        String clsName;
        if (matcher.find()) {
            clsName = matcher.group(1);
            System.out.println("className:"+clsName);
        } else {
            throw new IllegalArgumentException("No such class name in " + code);
        }

        //在对应代码生成目录中以时间戳为目录名建立目录
        File classRootFile = new File(PROJECT_DIR, TMP_DIR);
        final String time = String.valueOf(System.currentTimeMillis());
        File parentDir = new File(classRootFile, time);
        if (!parentDir.exists()) {
            parentDir.mkdir();
        }

        File classFile = new File(parentDir, clsName + ".class");
        File[] outputs = new File[]{parentDir};

        //开始进行编译
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> collector = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        fileManager.setLocation(StandardLocation.CLASS_PATH, CLASSPATH);
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(outputs));

        JavaFileObject javaFileObject = new MyJavaFileObject(clsName, code);
        Boolean result = compiler.getTask(null, fileManager, collector, OPTIONS, null, Collections.singletonList(javaFileObject)).call();

        //编译结果，如果有错误，返回对应错误信息
        if (!result) {
            List list = collector.getDiagnostics();
            StringBuilder info = new StringBuilder();
            for (Object object : list) {
                Diagnostic d = (Diagnostic) object;
                String line = d.getMessage(Locale.ENGLISH);
                info.append(line).append("\n");
            }
            String infoStr = info.toString();
            if (infoStr.endsWith("\n")) {
                infoStr = infoStr.substring(0, infoStr.length() - 1);
            }
            return "编译失败:" + infoStr;
        }

        //读取字节码，使用类加载器加载
        byte[] classBytes = getBytesFromFile(classFile);
        MyClassLoader classloader = new MyClassLoader();
        try {
            Class clazz = classloader.getTestClass(classBytes);
            Object instance = clazz.newInstance();

            Method method = clazz.getMethod("execute");
            return method.invoke(instance).toString();
        } catch (NoSuchMethodException e) {
            return "请实现execute无参方法";
        } catch (Exception e2) {
            return e2.getMessage();
        }
    }

    /**
     * 文件转化为字节数组
     *
     * @param file 文件
     * @return 字节数组
     */
    private static byte[] getBytesFromFile(File file) throws IOException {
        if (file == null) {
            return null;
        }
        FileInputStream in = new FileInputStream(file);
        ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
        byte[] b = new byte[4096];
        int n;
        while ((n = in.read(b)) != -1) {
            out.write(b, 0, n);
        }
        in.close();
        out.close();
        return out.toByteArray();
    }
}