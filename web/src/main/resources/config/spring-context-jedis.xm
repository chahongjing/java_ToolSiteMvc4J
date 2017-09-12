<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="jedisPoolConfig" class="redis.clients.jedis.JedisPoolConfig">
        <property name="maxIdle" value="300"/> <!--最大能够保持idel状态的对象数-->
        <property name="maxTotal" value="60000"/><!--最大分配的对象数-->
        <property name="testOnBorrow" value="true"/><!--当调用borrow Oject方法时，是否进行有效性检查-->
    </bean>

    <bean id="jedisPool" class="redis.clients.jedis.JedisPool">
        <constructor-arg name="poolConfig" ref="jedisPoolConfig"/>
        <constructor-arg name="host" value="${jedis.host}"/>
        <constructor-arg name="port" value="${jedis.port}" type="int"/>
        <constructor-arg name="timeout" value="${jedis.timeout}" type="int"/>
        <!-- 如果没有密码，则去掉密码这个配置 -->
        <constructor-arg name="password" value="${jedis.auth}"/>
    </bean>
</beans>