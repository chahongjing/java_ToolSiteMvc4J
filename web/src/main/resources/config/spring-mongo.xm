<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:mongo="http://www.springframework.org/schema/data/mongo"
       xsi:schemaLocation="http://www.springframework.org/schema/data/mongo
        http://www.springframework.org/schema/data/mongo/spring-mongo-1.0.xsd
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">

    <!-- 主机 -->
    <mongo:mongo host="${mongodb.host}" port="${mongodb.port}">
    </mongo:mongo>

    <!-- 数据库，用户名和密码 -->
    <mongo:db-factory id="mongoDbFactory" mongo-ref="mongo"
                      dbname="${mongodb.dbname}" username="${mongodb.username}" password="${mongodb.password}"/>

    <!-- mongodb的模板 -->
    <bean id="mongoTemplate" class="org.springframework.data.mongodb.core.MongoTemplate">
        <constructor-arg name="mongoDbFactory" ref="mongoDbFactory"/>
    </bean>
</beans>