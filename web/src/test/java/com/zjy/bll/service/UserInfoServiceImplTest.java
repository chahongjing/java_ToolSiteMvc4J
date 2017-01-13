package com.zjy.bll.service;

import com.zjy.bll.common.BaseTestCase;
import com.zjy.entities.UserInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by chahongjing on 2017/1/13.
 */
public class UserInfoServiceImplTest extends BaseTestCase {
    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void testtr() {
        userInfoService.testtr();
    }
}