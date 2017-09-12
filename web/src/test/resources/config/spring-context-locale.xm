<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- 国际化 ①注册资源Bean,其Bean名称只能为messageSource; 如果使用fmt标签，要经过mvc才有效; native2ascii转-->
    <bean id="messageSource"
          class="org.springframework.context.support.ResourceBundleMessageSource">
        <property name="defaultEncoding" value="utf-8"/>
        <property name="useCodeAsDefaultMessage" value="true"/>
        <property name="basenames">
            <list>
                <value>messages</value>
            </list>
        </property>
    </bean>
    <bean id="localeResolver" class="org.springframework.web.servlet.i18n.SessionLocaleResolver">
        <!--<property name="defaultLocale" value="en_US"></property>-->
    </bean>
</beans>