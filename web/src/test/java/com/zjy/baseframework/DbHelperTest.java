package com.zjy.baseframework;

import com.zjy.bll.common.BaseTestCase;
import com.zjy.entities.UserInfo;
import org.junit.Test;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by chahongjing on 2017/1/3.
 */
public class DbHelperTest extends BaseTestCase {
    DbHelper dbHelper = new DbHelper();
    @Test
    public void testSelect() throws Exception {
        ResultSet rs = dbHelper.testSelect();
        if(rs.next()) {
            System.out.println("首行首列：" + rs.getString(1));
        } else {
            System.out.println("没有数据");
        }
    }

    @Test
    public void toList() throws Exception {
        List<UserInfo> list = dbHelper.toList(UserInfo.class);
    }

    @Test
    public void testInsert() throws Exception {
        System.out.println("插入受影响行数：" + dbHelper.testInsert());
    }

    @Test
    public void testUpdate() throws Exception {
        System.out.println("更新受影响行数：" + dbHelper.testUpdate());
    }

    @Test
    public void testDelete() throws Exception {
        System.out.println("删除受影响行数：" + dbHelper.testDelete());
    }
}