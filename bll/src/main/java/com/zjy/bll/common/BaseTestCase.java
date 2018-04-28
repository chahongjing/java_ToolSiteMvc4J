package com.zjy.bll.common;

import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author chahongjing
 * @create 2016-12-15 20:52
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
//@WebAppConfiguration // 如果不是在web项目中，则添加此注解运行spring mvc环境
@ContextConfiguration({"/applicationContext.xml", "/config/spring-*.xml"}) //加载配置文件
//------------如果加入以下代码，所有继承该类的测试类都会遵循该配置，也可以不加，在测试类的方法上///控制事务，参见下一个实例
//这个非常关键，如果不加入这个注解配置，事务控制就会完全失效！
//@Transactional
//这里的事务关联到配置文件中的事务控制器（transactionManager = "transactionManager"），同时//指定自动回滚（defaultRollback = true）。这样做操作的数据才不会污染数据库！
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//------------
public class BaseTestCase extends TestCase {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
}
