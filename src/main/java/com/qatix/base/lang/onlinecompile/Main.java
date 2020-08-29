package com.qatix.base.lang.onlinecompile;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String source = "package com.qatix.base.lang.onlinecompile;" + //这一行不是必要的
                "\n" +
                "/**\n" +
                " * 测试class\n" +
                " * Created by Logan on 2020-08-29\n" +
                " */\n" +
                "public class FooClass2 { \n" +
                "    public String execute() {\n" +
                "        System.out.println(\"invoke method2\");" +
                "        return \"SUCCESS\";" +
                "    }" +
                "}";
        String result = MyCodeRuntime.run(source);
        System.out.println(result);
    }
}
