package com.zjy.web.controller;

import com.alibaba.fastjson.JSON;
import com.zjy.baseframework.MongoDbHelper;
import com.zjy.baseframework.PropertiesHelper;
import com.zjy.bll.common.BaseTestCase;
import com.zjy.entities.Sex;
import com.zjy.entities.UserInfo;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by chahongjing on 2016/12/12.
 */
public class IndexControllerTest extends BaseTestCase {

    @Resource
    private IndexController indexController;

    //@Resource
    //private MongoTemplate mongoTemplate;

    //@Resource
    //private JdbcTemplate jdbcTemplate;

    @Test
    @Transactional   //标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void testTest1() {
        //new MongoDbHelper().test();
        PropertiesHelper instance = PropertiesHelper.getInstance();
        logger.info("db.url:" + instance.getProperties("db.url"));
        List<UserInfo> userInfos = new ArrayList<>();

        UserInfo user = new UserInfo();
        user.setUserCode("zjy");
        user.setUserName("曾军毅");
        userInfos.add(user);

        user = new UserInfo();
        user.setUserCode("zs");
        user.setUserName("张三");
        userInfos.add(user);

        user = new UserInfo();
        user.setUserCode("ls");
        user.setUserName("李四");
        userInfos.add(user);

        user = new UserInfo();
        user.setUserCode("ww");
        user.setUserName("王五");
        userInfos.add(user);

        Map<String, UserInfo> collect = userInfos.parallelStream().collect(Collectors.toMap(item -> item.getUserCode(), item -> item));
        logger.info("toMap：" + JSON.toJSONString(collect));

        userInfos = userInfos.parallelStream().map(item -> {
            item.setPassword("1");
            return item;
        }).collect(Collectors.toList());
        logger.info("map：" + JSON.toJSONString(collect));

        List<String> userCodes = userInfos.parallelStream().peek(item -> item.setPassword("abc")).map(UserInfo::getUserCode).collect(Collectors.toList());
        userCodes.stream().peek(item -> System.out.printf("peek" + item)).map(String::toUpperCase).peek(item -> System.out.println("up:" + item))
                .collect(Collectors.toList());
        logger.info("map：" + JSON.toJSONString(collect));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.error("系统异常", e);
        }
        logger.info("IndexController().test()");

        //jdbcTemplate.update("select ....", p1, p2)
    }

    @Test
    public void test2() {
        UserInfo user = new UserInfo();
        user.setUserGuid("D8E6B877-3645-4063-A25C-495606B95349");
        user.setUserCode("testuser");
        user.setUserName("测试数据");
        user.setPassword("1");
        user.setSex(Sex.Male);
        user.setBirthday(new Date());
        user.setIsSystem(true);
        //mongoTemplate.insert(user, "testcollection");
        List<UserInfo> list = get(UserInfo.class);
    }

    public <T> List<T> get(Class clazz) {
        //return mongoTemplate.findAll(clazz, "testcollection");
    	return null;
    }
}