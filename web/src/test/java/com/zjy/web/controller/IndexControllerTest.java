package com.zjy.web.controller;

import com.alibaba.fastjson.JSON;
import com.zjy.baseframework.MongoDbHelper;
import com.zjy.baseframework.PropertiesHelper;
import com.zjy.bll.common.BaseTestCase;
import com.zjy.entities.UserInfo;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by chahongjing on 2016/12/12.
 */
public class IndexControllerTest extends BaseTestCase {

    @Resource
    private IndexController indexController;

    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    @Transactional   //标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void testTest1() throws Exception {
        indexController.test();
        new MongoDbHelper().test();
        PropertiesHelper instance = PropertiesHelper.getInstance();
        logger.info("db.url:" + instance.getProperties("db.url"));
        List<UserInfo> userInfos = new ArrayList<>();
        userInfos.add(new UserInfo() {{
            setUserCode("zjy");
            setUserName("曾军毅");
        }});
        userInfos.add(new UserInfo() {{
            setUserCode("zs");
            setUserName("张三");
        }});
        userInfos.add(new UserInfo() {{
            setUserCode("ls");
            setUserName("李四");
        }});
        userInfos.add(new UserInfo() {{
            setUserCode("ww");
            setUserName("王五");
        }});

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

        Thread.sleep(2000);
        logger.info("IndexController().test()");
    }
}