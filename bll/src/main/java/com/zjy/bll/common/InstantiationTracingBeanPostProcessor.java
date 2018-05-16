package com.zjy.bll.common;

import com.zjy.baseframework.mybatis.CodeEnumUtil;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/5/16.
 */
@Component
public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private SqlSessionFactoryBean sqlSessionFactory;

    /**
     * spring容器加载完成后事件
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
        if (event.getApplicationContext().getParent() == null) {
            //root application context 没有parent，他就是老大.
            // 需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
            //sqlSessionFactory = (SqlSessionFactoryBean) SpringContextHolder.getBean("sqlSessionFactory");
            try {
                CodeEnumUtil.registerTypeHandle(sqlSessionFactory.getObject().getConfiguration().getTypeHandlerRegistry());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
