package com.zjy.baseframework;

import com.alibaba.fastjson.JSON;
import com.zjy.bll.common.BaseTestCase;
import com.zjy.entities.enums.Sex;
import com.zjy.entities.UserInfo;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */
public class ExcelHelperTest extends BaseTestCase {

    @Test
    public void testListToExcel() {
        System.out.println(String.format("%1s%2$02d%3$02d%4$02d", "asb", 1, 2, 0));
        List<UserInfo> list = new ArrayList<>();
        LinkedHashMap<String, String> headers = new LinkedHashMap<>();
        headers.put("userCode", "用户编码");
        headers.put("userName", "用户名称");
        headers.put("createdOn", "创建时间");
        headers.put("sex", "性别");
        UserInfo user = new UserInfo();
        user.setUserCode("1");
        user.setUserName("第一个");
        user.setCreatedOn(new Date());
        user.setSex(Sex.Male);
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
        user.setUserCode("6");
        user.setUserName("第六个");
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

        String file = "d:\\a.xls";
        ExcelHelper.listToExcelNew(list, headers, "sheet名称", file);
        file = "d:\\a.xlsx";
        ExcelHelper.listToExcelNew(list, headers, "sheet名称", file);
    }

    @Test
    public void testExcelToList() {
        LinkedHashMap<String, String> headers = new LinkedHashMap<>();
        headers.put("userCode", "用户编码");
        headers.put("userName", "用户名称");
        headers.put("createdOn", "创建时间");
        headers.put("sex", "性别");
        try {
            File f = new File("d:\\a.xls");
            FileInputStream ins = null;
            ins = new FileInputStream(f);
            List<UserInfo> list = ExcelHelper.excelToList(ins, "sheet名称", UserInfo.class, headers);
            System.out.println(JSON.toJSONString(list));
            ins.close();

            f = new File("d:\\a.xlsx");
            ins = null;
            ins = new FileInputStream(f);
            list = ExcelHelper.excelToList(ins, "sheet名称", UserInfo.class, headers);
            System.out.println(JSON.toJSONString(list));
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}