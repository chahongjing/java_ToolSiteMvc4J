package com.zjy.web.controller;

import com.zjy.common.BaseTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by chahongjing on 2017/1/2.
 */
public class TestControllerTest extends BaseTestCase {

    //region 自动装配
    @Autowired
    private TestController testController;
    //endregion

    @Before
    public void setUp() throws Exception {

    }

    //region solr测试
    @Test
    public void solrAdd() throws Exception {
        testController.solrAdd();
    }

    @Test
    public void solrAddList() throws Exception {
        testController.solrAddList();
    }

    @Test
    public void solrDelete() throws Exception {
        testController.solrDelete();
    }

    @Test
    public void solrFind() throws Exception {
        testController.solrFind();
    }
    //endregion
}