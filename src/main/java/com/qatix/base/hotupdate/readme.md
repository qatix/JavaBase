参考
https://www.cnblogs.com/wgslucky/p/9127681.html

JDK代理的两种方式：

1.premain方式是Java SE5开始就提供的代理方式，但其必须在命令行指定代理jar，并且代理类必须在main方法前启动，它要求开发者在应用启动前就必须确认代理的处理逻辑和参数内容等等

2.agentmain方式是JavaSE6开始提供，它可以在应用程序的VM启动后再动态添加代理的方式

应用场景：

premain这种方式必须在jar包启动的时候进行指定，它是运行在项目的main方法之前的，即项目启动时：

1
java – javaagent:LoadAgent.jar -jar GameServer.Jar
 

但是正常的生产环境下，一般不会开启代理功能，但是在发生问题时，我们不希望停止应用就能够动态的去修改一些类的行为，以帮助排查问题，这在应用启动前是无法确定的。这时agentmain就可以做到了。所以我们采用agentmain这种方式。

1，LoadAgent实现

这个实现也比较简单，就像我们的程序入口有main方法一样，它需要一个agemtmain方法


复制代码
public class GameServerAgent {

    public static void agentmain(String args, Instrumentation inst) throws Exception {
        
        System.out.println("agent 启动成功,开发重定义对象....");

        Class<?>[] allClass = inst.getAllLoadedClasses();
        for (Class<?> c : allClass) {
            if (c.getName().endsWith("TestHot")) {
                String pathname = "config\\TestHot.class";
                File file = new File(pathname);
                try {
                    byte[] bytes = fileToBytes(file);
                    System.out.println("文件大小：" + bytes.length);
                    ClassDefinition classDefinition = new ClassDefinition(c, bytes);
                    inst.redefineClasses(classDefinition);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("转换代码。。。");
            }
        }
        System.out.println("热更新成功....");

    }

    public static byte[] fileToBytes(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        in.close();
        return bytes;
    }
}
复制代码
对某个class的替换有两种方式

1，使用ClassFileTransformer

2，使用ClassDefinition

由于ClassDefinition比较方便，所以我们使用ClassDefinition对类进行更新。

项目源码地址：https://github.com/youxijishu/game-hot-update

热更新步骤：

1，打包LoadAgent

 在使用LoadAgent的时候，需要在MANNIFEST.MF添加一些属性

1
2
3
Agent-Class: com.xinyue.hot.agent.GameServerAgent
Can-Redefine-Classes: true
Can-Retransform-Classes: true
 这个在可以在打包的pom.xml中配置

复制代码
 <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>2.3.1</version>
                <configuration>
                    <archive>
                        <manifest>
                            <addClasspath>true</addClasspath>

                        </manifest>
                        <manifestEntries>
                            <Agent-Class>
                                com.xinyue.hot.agent.GameServerAgent
                            </Agent-Class>
                            <Can-Redefine-Classes>true</Can-Redefine-Classes>
                            <Can-Retransform-Classes>true</Can-Retransform-Classes>
                        </manifestEntries>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>
复制代码
然后使用mvn install命令即可，在target中找到生成的jar，LoadAgent-0.0.1-SNAPSHOT.jar，把它放到GameServer下的config目录下面，这里是测试，用的相对路径。

2，在GameServer中创建一个测试的类，叫TestHop.java，使用里面有一个输出方法，打印1，然后使用mvn install进行编译，在target/classes中找到这个类，把它也复制到GameServer的config路径下面。

3，把TestHop类中的输出修改为2，运行GameServer，这时会输出pid和2

4，把pid复制到GameServerHotUpdate的HotUpdateMain类中，当然，在实际应用中也可以通过args传进来。然后运行HotUpdateMain，等会儿就会输出结果