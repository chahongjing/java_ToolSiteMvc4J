package com.zjy.bll.common;

import com.zjy.bll.service.TestService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by chahongjing on 2017/12/11.
 */
public class LoggingProxy {
    // 要代理的对象，如计算器类，此处只是举个例子
    private TestService target;

    public LoggingProxy(TestService target) {
        this.target = target;
    }

    public TestService getLoggingProxy() {
        TestService proxy = null;

        // 代理对象的加载器
        ClassLoader loader = target.getClass().getClassLoader();
        // 代理对象的类型，代理的方法
        Class[] interfaces = new Class[]{TestService.class};
        // 调用代理对象的方法时执行的代码
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 一般不直接有proxy对象，因为调用proxy对象的方法时又会进入此方法，导致死循环
                String name = method.getName();
                System.out.println("method:" + name + "执行前操作！");
                Object result = method.invoke(target, args);
                System.out.println("method:" + name + "执行后操作！proxy result:" + result);
                return result;
            }
        };

        proxy = (TestService) Proxy.newProxyInstance(loader, interfaces, handler);
        return proxy;
    }
}
