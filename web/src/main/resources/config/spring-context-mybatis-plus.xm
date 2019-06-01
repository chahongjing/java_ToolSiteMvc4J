<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/tx
       http://www.springframework.org/schema/tx/spring-tx.xsd
       http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-4.0.xsd">

    <!-- Spring整合Mybatis，更多查看文档：http://mp.baomidou.com -->
    <bean id="sqlSessionFactory" class="com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!-- 自动扫描Mapping.xml文件 -->
        <property name="mapperLocations" value="classpath*:mapper/**/*Mapper.xml"/>
        <!--<property name="configLocation" value="classpath:mybatis/mybatis-config.xml"/>-->
        <property name="typeAliasesPackage" value="com.zjy.entities"/>
        <property name="typeEnumsPackage" value="com.zjy.entities.enums"/>
        <property name="plugins">
            <array>
                <!--&lt;!&ndash; 分页插件配置 &ndash;&gt;-->
                <!--<bean id="paginationInterceptor" class="com.baomidou.mybatisplus.plugins.PaginationInterceptor">-->
                <!--</bean>-->
                <bean class="com.github.pagehelper.PageHelper">
                </bean>
            </array>
        </property>
        <!-- 全局配置注入 -->
        <property name="globalConfig" ref="globalConfig" />
    </bean>
    <bean id="globalConfig" class="com.baomidou.mybatisplus.entity.GlobalConfiguration">
        <!--
            AUTO->`0`("数据库ID自增")
             INPUT->`1`(用户输入ID")
            ID_WORKER->`2`("全局唯一ID")
            UUID->`3`("全局唯一ID")
        -->
        <property name="idType" value="2" />
        <!--
            MYSQL->`mysql`
            ORACLE->`oracle`
            DB2->`db2`
            H2->`h2`
            HSQL->`hsql`
            SQLITE->`sqlite`
            POSTGRE->`postgresql`
            SQLSERVER2005->`sqlserver2005`
            SQLSERVER->`sqlserver`
        -->
        <!-- Oracle需要添加该项 -->
        <property name="dbType" value="sqlite" />
        <!-- 全局表为下划线命名设置 true -->
        <!-- <property name="dbColumnUnderline" value="true" /> -->
        <!--<property name="metaObjectHandler">-->
        <!--<bean class="com.baomidou.springmvc.common.MyMetaObjectHandler" />-->
        <!--</property>-->
    </bean>



    <!--<bean id="sqlSessionFactory1" class="org.mybatis.spring.SqlSessionFactoryBean">-->
    <!--<property name="dataSource">-->
    <!--<ref bean="dataSource"></ref>-->
    <!--</property>-->
    <!--&lt;!&ndash; 自动扫描entity目录, 省掉Configuration.xml里的手工配置 &ndash;&gt;-->
    <!--<property name="typeAliasesPackage" value="com.zjy.entities"/>-->
    <!--&lt;!&ndash; 显式指定Mapper文件位置 &ndash;&gt;-->
    <!--<property name="mapperLocations" value="classpath*:mapper/**/*Mapper.xml"/>-->

    <!--<property name="plugins">-->
    <!--<array>-->
    <!--<bean class="com.github.pagehelper.PageHelper">-->
    <!--</bean>-->
    <!--</array>-->
    <!--</property>-->
    <!--</bean>-->
    <!-- 扫描basePackage下所有以@Repository标识的 接口-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.zjy.bll.dao"/>
        <property name="annotationClass" value="org.springframework.stereotype.Repository"/>
    </bean>

    <!-- 配置mybatis事务管理器 -->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <!-- 第一种开启事务控制的注解支持, 添加@Transactional注解即可 -->
    <tx:annotation-driven transaction-manager="transactionManager"/>

    <!--&lt;!&ndash; 第二种：1)声明式容器事务管理 ,transaction-manager指定事务管理器为transactionManager &ndash;&gt;-->
    <!--<tx:advice id="txAdvice" transaction-manager="transactionManager">-->
    <!--<tx:attributes>-->
    <!--<tx:method name="add*" propagation="REQUIRED"/>-->
    <!--<tx:method name="get*" propagation="REQUIRED"/>-->
    <!--<tx:method name="*" read-only="true"/>-->
    <!--</tx:attributes>-->
    <!--</tx:advice>-->

    <!--&lt;!&ndash; 第二种： 2）声明切入点 &ndash;&gt;-->
    <!--<aop:config expose-proxy="true">-->
    <!--&lt;!&ndash; 只对业务逻辑层实施事务 &ndash;&gt;-->
    <!--&lt;!&ndash;服务命名空间 &ndash;&gt;-->
    <!--<aop:pointcut id="txPointcut" expression="execution(* com.zjy.bll.service..*.*(..))"/>-->
    <!--&lt;!&ndash; Advisor定义，切入点和通知分别为txPointcut、txAdvice &ndash;&gt;-->
    <!--<aop:advisor pointcut-ref="txPointcut" advice-ref="txAdvice"/>-->
    <!--</aop:config>-->

    <!-- 配置事务异常封装 -->
    <bean id="persistenceExceptionTranslationPostProcessor"
          class="org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor"/>
</beans>