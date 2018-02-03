package com.zjy.baseframework;

import com.zjy.bll.common.BaseTestCase;
import org.junit.Test;

/**
 * Created by chahongjing on 2017/1/3.
 */
public class DbHelperTest extends BaseTestCase {
    DbHelper dbHelper = new DbHelper();
    @Test
    public void testSelect() {
        // rs已关闭，异常
//        ResultSet rs = dbHelper.testSelect();
//        try {
//            if(rs.next()) {
//                System.out.println("首行首列：" + rs.getString(1));
//            } else {
//                System.out.println("没有数据");
//            }
//        } catch (SQLException e) {
//            logger.error("数据库操作异常", e);
//        }
    }

    @Test
    public void toList() {
        //List<UserInfo> list = dbHelper.toList(UserInfo.class);
    }

    @Test
    public void testInsert() {
        //System.out.println("插入受影响行数：" + dbHelper.testInsert());
    }

    @Test
    public void testUpdate() {
        //System.out.println("更新受影响行数：" + dbHelper.testUpdate());
    }

    @Test
    public void testDelete() {
        //System.out.println("删除受影响行数：" + dbHelper.testDelete());
    }
}