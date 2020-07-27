
# 参考
https://ezlippi.com/blog/2019/05/trace-context-bwtween-threads.html


```aidl

在分布式系统的上下文传递过程中，需要传递的信息一般包括traceID、 spanID以及部分请求参数等,可以分为以下几种场景:

在同一线程内传递
跨线程传递
跨应用传递
在同一个线程内传递比较简单,通过ThreadLocal就能解决上下文传递的问题,如果是跨线程传递,大家可能会想到jdk里的实现java.lang.InheritableThreadLocal,它拥有和线程变量ThreadLocal一样的功能，并且在当前线程上创建一个新的线程实例时，会把这些线程变量从当前线程传递给新的线程实例.但是在实际的应用场景里，绝大多数都是使用线程池来进行多线程编程，线程由线程池创建好，并且线程是池化起来反复使用,这时父子线程关系的ThreadLocal值传递已经没有意义，应用需要的实际上是把任务提交给线程池时的ThreadLocal值传递到任务执行时。所以jdk提供的inheritableThreadLocals类实用性不高,在线程池(ThreadPoolExecutor)中运行一个Runable实例并不会去新建一个线程，而是把Runable实例添加到队列中(在核心线程数已实例化满的时候),让ThreadPoolExecutor的workers去从队列里拿出Runable实例（这是一个典型的生产者消费者模式),然后运行Runable实例.run()方法,故jdk的inheritableThreadLocals这种实现方式没法适用。

这篇文章主要介绍基于字节码修改的方法修改ThreadPoolExecutor和ForkJoinTask的字节码,实现非侵入式的上下文传递,我们先来看一下如果通过侵入式方式定制线程池怎么解决上下文传递,假设我们的调用链通过TraceContext类来保存上下文信息:

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
我们先定义2个类,分别继承自Runnable和Callable,目的在于初始化Runnable和Callable实例时保存调用线程的上下文信息到,在执行run()或者call()方法时,先把调用线程的上下文信息设置到当前执行的线程中,run()/call()方法执行后恢复执行线程的上下文,这2个类分别为TraceRunnable和TraceCallable:

public class TraceRunnable implements Runnable {
    //在初始化TraceRunnable时会获取调用线程的上下文
    private final Object context = TraceContext.getContext();
    private final Runnable runnable;

    public TraceRunnable(Runnable runnable) {
        this.runnable = runnable;
    }
    @Override
    public void run() {
        Object backup = TraceContextUtil.backupAndSet(this.context);

        try {
            this.runnable.run();
        } finally {
            TraceContextUtil.restoreBackup(backup);
        }
    }

    public Runnable getRunnable() {
        return this.runnable;
    }

    public static TraceRunnable get(Runnable runnable) {
        if (runnable == null) {
            return null;
        } else {
            return runnable instanceof TraceRunnable ? (TraceRunnable)runnable : new TraceRunnable(runnable);
        }
    }
}

public class TraceCallable<V> implements Callable<V> {
    //在初始化TraceCallable时会获取调用线程的上下文
    private final Object context = TraceContext.getContext();
    private final Callable<V> callable;

    public TraceCallable(Callable<V> callable) {
        this.callable = callable;
    }

    @Override
    public V call() throws Exception {
        Object backup = TraceContextUtil.backupAndSet(this.context);

        V result;
        try {
            result = this.callable.call();
        } finally {
            TraceContextUtil.restoreBackup(backup);
        }

        return result;
    }

    public Callable<V> getCallable() {
        return this.callable;
    }
    //返回TraceCallable实例
    public static <T> TraceCallable<T> get(Callable<T> callable) {
        if (callable == null) {
            return null;
        } else {
            return callable instanceof TraceCallable ? (TraceCallable)callable : new TraceCallable(callable);
        }
    }

}

public class TraceContextUtil {
    //设置调用线程的上下文到当前执行线程中,并返回执行线程之前的上下文
    public static Object backupAndSet(Object currentContext) {
        Object backupContext = TraceContext.getContext();
        TraceContext.setContext(currentContext);
        return backupContext;
    }
    //恢复执行线程的上下文
    public static void restoreBackup(Object backup) {
        TraceContext.setContext(backup);
    }
}
接下来就是定制线程池,重写execute和submit方法,把Runnable/Callable实例封装成TraceRunnable/TraceCallable,这样就把调用线程的上下文信息传递到线程池内部:

public class TraceThreadPoolExecutor extends java.util.concurrent.ThreadPoolExecutor{
    public void submit(Runnable runnable) {
        TraceRunnable traceRunnable = new TraceRunnable(runnable);
        super.execute(traceRunnable);
    }
    
    public Future<?> submit(Runnable task) {
        TraceRunnable traceRunnable = new TraceRunnable(runnable);
        return super.submit(traceRunnable);
    }
    public <T> Future<T> submit(Callable<T> task) {
         TraceCallable traceCallable = new TraceCallable(task);
         return super.submit(traceCallable);
    }
}
上面的方法虽然实现了上下文跨线程传递,在Runnable/Callable方法内部可以通过TraceContext.getContext()来获取上下文信息,但前提是异步处理都要使用TraceThreadPoolExecutor来提交任务,这样对代码具有侵入性,需要业务做改造,
为了减少业务开发人员的工作量，使用 javaagent和instrument技术，利用字节码修改技术修改ThreadPoolExecutor和ScheduledThreadPoolExecutor类的字节码,也就是说,只要在JVM参数里加上了javaagent的配置，不需要直接使用上面的TraceRunnable/TraceCallable包装类，也不需要使用TraceThreadPoolExecutor,即可实现上下文信息的自动传递。

对于ThreadPoolExecutor和ScheduledThreadPoolExecutor,只需要修改它们的execute/submit/schedule/scheduleAtFixedRate/scheduleWithFixedDelay这些方法的字节码,逻辑和前面介绍的一致:

public interface TraceTransformer {
    boolean needTransform(String className);

    void doTransform(CtClass var1) throws NotFoundException, CannotCompileException, IOException;
}

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
对于ForkJoinPool, 做法也类似,这里换一种修改方式,主要为了熟悉下javassist的语法,这次是在ForkJoinTask里添加一个context$field$add$by$trace字段,在初始化ForkJoinTask实例自动获取调用线程的上下文(TraceContext.getContext()),并修改ForkJoinTask的doExec()方法,修改逻辑和前面的一样,

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
        clazz.addField(contextField, "com.ezlippi.trace.agent.context.TraceContext.getContext();");
        System.out.println("add new field context$field$add$by$trace to class " + className);
        CtMethod doExecMethod = clazz.getDeclaredMethod("doExec");
        CtMethod newDoExecMethod = CtNewMethod.copy(doExecMethod, "doExec", clazz, (ClassMap)null);
        doExecMethod.setName("original$doExec$method$renamed$by$trace");
        doExecMethod.setModifiers(doExecMethod.getModifiers() & -2 | 2);
        //java.lang.Object backup = com.ezlippi.trace.agent.context.TraceContextUtil.backupAndSet(this.context$field$add$by$trace);
        //try {
        //   return original$doExec$method$renamed$by$trace($$);
        //} finally {
        //    TraceContextUtil.restoreBackup(backup);
        //}
        newDoExecMethod.setBody("{\njava.lang.Object backup = com.ezlippi.trace.agent.context.TraceContextUtil.backupAndSet(context$field$add$by$trace);\ntry {\n    return original$doExec$method$renamed$by$trace($$);\n} finally {\n    com.ezlippi.trace.agent.context.TraceContextUtil.restoreBackup(backup);\n}\n}");
        clazz.addMethod(newDoExecMethod);
        System.out.println("insert code around method " + doExecMethod + " of class " + className);


    }
}
接下来就是添加一个ClassFileTransformer,JVM启动时会传递Instrumentation对象给javaagent的preMain()方法,我们只需要往instrumentation中注册一个ClassFileTransformer实例,jvm在加载类时会把解析后的class字节数组传递给
ClassFileTransformer,执行修改逻辑后把字节数组返回给jvm.

public class TlTransformer implements ClassFileTransformer {
    private List<TraceTransformer> transformers = new ArrayList<>();

    public TlTransformer() {
        this.transformers.add(new ForkJoinPoolTransformer());
        this.transformers.add(new ThreadPoolTransformer());
    }

    @Override
    public byte[] transform(ClassLoader loader, String classFile, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classFileBuffer) throws IllegalClassFormatException {
        try {
            if (classFile == null) {
                return new byte[0];
            }

            String className = this.toClassName(classFile);
            Iterator iterator = this.transformers.iterator();

            while(iterator.hasNext()) {
                TraceTransformer transformer = (TraceTransformer)iterator.next();
                if (transformer.needTransform(className)) {
                    System.out.println("Transforming class " + className);
                    CtClass clazz = this.getCtClass(classFileBuffer, loader);
                    transformer.doTransform(clazz);
                    return clazz.toBytecode();
                }
            }
        } catch (Throwable throwable) {
            String msg = "Fail to transform class " + classFile + ", cause: " + throwable.toString();
            System.out.println(msg);
            throw new IllegalStateException(msg, throwable);
        }

        return new byte[0];
    }

    private String toClassName(String classFile) {
        return classFile.replace('/', '.');
    }

    private CtClass getCtClass(byte[] classFileBuffer, ClassLoader classLoader) throws IOException {
        ClassPool classPool = new ClassPool(true);
        if (null != classLoader) {
            classPool.appendClassPath(new LoaderClassPath(classLoader));
        }

        CtClass clazz = classPool.makeClass(new ByteArrayInputStream(classFileBuffer), false);
        clazz.defrost();
        return clazz;
    }
}

public class TraceAgent {
    public static void premain(String agentArgs, Instrumentation instrumentation) {
        ClassFileTransformer transformer = new TlTransformer();
        instrumentation.addTransformer(transformer, true);
    }
}
因为修改了JDK的标准库的类，标准库由bootstrap class loader加载,上面修改后的ThreadPoolExecutor和ForkJoinTask类引用了agent的代码，所以agent的Jar需要加到boot class path上,可以通过maven-jar-plugin在agent jar的manifest添加Boot-Class-Path这个入口.

<plugin>
        <artifactId>maven-jar-plugin</artifactId>
        <version>3.0.2</version>
        <configuration>
            <archive>
                <manifestEntries>
                    <Premain-Class>com.ezlippi.trace.agent.TraceAgent</Premain-Class>
                    <Boot-Class-Path>${project.artifactId}-${project.version}.jar</Boot-Class-Path>
                    <Can-Redefine-Classes>true</Can-Redefine-Classes>
                    <Can-Retransform-Classes>true</Can-Retransform-Classes>
                    <Can-Set-Native-Method-Prefix>false</Can-Set-Native-Method-Prefix>
                </manifestEntries>
            </archive>
        </configuration>
</plugin>
最后在Java的启动参数加上：-javaagent:path/to/trace-agent-x.x.x.jar后就大功告成了.
```