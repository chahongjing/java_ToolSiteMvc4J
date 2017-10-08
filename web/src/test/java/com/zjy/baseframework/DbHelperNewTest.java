package com.zjy.baseframework;

import com.zjy.bll.common.BaseTestCase;
import com.zjy.entities.UserInfo;
import org.junit.Test;

import java.util.List;

/**
 * Created by chahongjing on 2017/10/8.
 */
public class DbHelperNewTest extends BaseTestCase {
    @Test
    public void get() throws Exception {
        UserInfo u = DbHelperNew.get("select * from userinfo", UserInfo.class);
        int i = DbHelperNew.update("update userinfo set username = '曾' where username = '1曾'");
        List<UserInfo> list = DbHelperNew.getList("select * from userinfo", UserInfo.class);
    }

}