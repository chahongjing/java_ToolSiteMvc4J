<?xml version="1.0" encoding="UTF-8"?>
<config xmlns="http://www.springframework.org/schema/config"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xsi:schemaLocation="http://www.springframework.org/schema/config http://www.springframework.org/schema/config/spring-config.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.2.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.2.xsd">

	<bean id="sessionFactory"
		class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
		<property name="dataSource" ref="dataSource" />
		<property name="packagesToScan">
			<list>
				<!-- 可以加多个包, 实体所在包名 -->
				<value>entities</value>
			</list>
		</property>
		<property name="hibernateProperties">
			<props>
				<prop key="hibernate.hbm2ddl.auto">${hibernate.hbm2ddl.auto}</prop>
				<prop key="hibernate.dialect">${hibernate.dialect}</prop>
				<prop key="hibernate.show_sql">${hibernate.show_sql}</prop>
				<!-- <prop key="hibernate.current_session_context_class">thread</prop> -->
			</props>
		</property>
	</bean>

	<!-- 配置Hibernate事务管理器 -->
	<bean id="transactionManager"
		class="org.springframework.orm.hibernate5.HibernateTransactionManager">
		<property name="sessionFactory" ref="sessionFactory" />
	</bean>

	<!-- 声明式容器事务管理 ,transaction-manager指定事务管理器为transactionManager -->
	<tx:advice id="txAdvice" transaction-manager="transactionManager">
		<tx:attributes>
			<tx:method name="add*" propagation="REQUIRED" />
			<tx:method name="get*" propagation="REQUIRED" />
			<tx:method name="*" read-only="true" />
		</tx:attributes>
	</tx:advice>

	<!-- 配置事务异常封装 -->
	<bean id="persistenceExceptionTranslationPostProcessor"
		class="org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor" />

	<aop:config expose-proxy="true">
		<!-- 只对业务逻辑层实施事务 -->
		<!--服务命名空间 -->
		<aop:pointcut id="txPointcut" expression="execution(* services..*.*(..))" />
		<!-- Advisor定义，切入点和通知分别为txPointcut、txAdvice -->
		<aop:advisor pointcut-ref="txPointcut" advice-ref="txAdvice" />
	</aop:config>

	<!-- 业务服务类, 构造bean -->
	<bean id="userInfoDao" class="daos.UserInfoDao">
		<property name="sessionFactory">
			<ref bean="sessionFactory"></ref>
		</property>
	</bean>
	<bean id="userInfoService" class="services.UserInfoService">
		<property name="userInfoDao">
			<ref bean="userInfoDao"></ref>
		</property>
	</bean>
</config>