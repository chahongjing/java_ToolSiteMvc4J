package com.zjy.bll.service;

import com.zjy.bll.common.BaseTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by chahongjing on 2017/1/13.
 */
public class UserInfoServiceImplTest extends BaseTestCase {
    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void testtr() {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("config/spring-locale.xml");
//        String key = "username";
//        Object[] arg = new Object[]{"Erica", Calendar.getInstance().getTime()};
//        String msg = ctx.getMessage(key, arg, Locale.CHINA);
//        String msgUs = ctx.getMessage(key, arg, Locale.US);
//        String msgCs = ctx.getMessage(key, arg, Locale.CANADA);
//        System.out.println("Message msg is ===> " + msg);
//        System.out.println("Message msgUs is ===> " + msgUs);
//        System.out.println("Message msgCs is ===> " + msgCs);

//        userInfoService.testtr();
    }
}