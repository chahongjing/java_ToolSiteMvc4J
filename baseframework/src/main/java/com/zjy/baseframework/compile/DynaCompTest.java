package com.zjy.baseframework.compile;

import java.lang.reflect.Method;

public class DynaCompTest
{
    public static void main(String[] args) throws Exception {
        String fullName = "com.zjy.baseframework.MyClass";
//        File file = new File("/Users/yangyu/Downloads/myclass");
//        InputStream in = new FileInputStream(file);
//        byte[] bytes = IOUtils.readFully(in, -1, false);
//        String src = new String(bytes);

        String code = "package com.zjy.baseframework;\n" +
                "\n" +
                "public class MyClass {\n" +
                "\n" +
                "    public String say(String str){\n" +
                "        return \"hello\"+str;\n" +
                "    }\n" +
                "}";
//        in.close();

//        System.out.println(code);
        DynamicEngine de = DynamicEngine.getInstance();
        Object instance =  de.javaCodeToObject(fullName, code);
//        System.out.println(instance);
        Method say = instance.getClass().getMethod("say", String.class);
        Object res = say.invoke(instance, "曾军毅");
        System.out.println("结果：" + res);
    }
}