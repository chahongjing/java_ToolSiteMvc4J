package com.zjy.bll.common;

import com.zjy.baseframework.EnumHelper;
import com.zjy.baseframework.ReflectionHelper;
import com.zjy.baseframework.mybatis.CodeEnumTypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2018/5/16.
 */
@Component
public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ApplicationEvent> {

    @Autowired
    private SqlSessionFactoryBean sqlSessionFactory;

    /**
     * spring容器加载完成后事件
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        //root application context 没有parent，他就是老大.
        if (((ContextRefreshedEvent) event).getApplicationContext().getParent() != null) return;
        //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
        if (event instanceof ContextClosedEvent) {
            System.out.println(event.getClass().getSimpleName() + " 事件已发生！");
        } else if (event instanceof ContextRefreshedEvent) {
            // 需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
            // 获取所有类型
            List<Class> classList = ReflectionHelper.getProjectClassList();
            // 注册mbatis枚举类
            try {
                //sqlSessionFactory = (SqlSessionFactoryBean) SpringContextHolder.getBean("sqlSessionFactory");
                CodeEnumTypeHandler.registerTypeHandle(sqlSessionFactory.getObject().getConfiguration().getTypeHandlerRegistry(), classList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 初始化要序列化的枚举
            EnumHelper.initAllSerializeEnum(classList);
            System.out.println(event.getClass().getSimpleName() + " 事件已发生！");
        } else if (event instanceof ContextStartedEvent) {
            System.out.println(event.getClass().getSimpleName() + " 事件已发生！");
        } else if (event instanceof ContextStoppedEvent) {
            System.out.println(event.getClass().getSimpleName() + " 事件已发生！");
        } else {
            System.out.println("有其它事件发生:" + event.getClass().getName());
        }
    }
}
