package com.zjy.baseframework;

import com.zjy.bll.common.BaseTestCase;
import com.zjy.entities.UserInfo;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */
public class ExcelHelperTest extends BaseTestCase {

    @Test
    public void testListToExcel() {
        List<UserInfo> list = new ArrayList<>();
        LinkedHashMap<String, String> headers = new LinkedHashMap<>();
        headers.put("userCode", "用户编码");
        headers.put("userName", "用户名称");
        UserInfo user = new UserInfo();
        user.setUserCode("1");
        user.setUserName("第一个");
        list.add(user);
        user = new UserInfo();
        user.setUserCode("2");
        user.setUserName("第二个");
        list.add(user);
        user = new UserInfo();
        user.setUserCode("3");
        user.setUserName("第三个");
        list.add(user);
        user = new UserInfo();
        user.setUserCode("4");
        user.setUserName("第四个");
        list.add(user);
        user = new UserInfo();
        user.setUserCode("5");
        user.setUserName("第五个");
        list.add(user);

        user = new UserInfo();
        user.setUserCode("7");
        user.setUserName("第七个");
        list.add(user);

        user = new UserInfo();
        user.setUserCode("8");
        user.setUserName("第八个");
        list.add(user);

        user = new UserInfo();
        user.setUserCode("9");
        user.setUserName("第九个");
        list.add(user);

        File f = new File("d:\\a.xls");
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(f);
            ExcelHelper.listToExcelNew(list, headers, "sheetName", os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}