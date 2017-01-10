package com.zjy.bll.job;

import com.alibaba.fastjson.JSON;
import com.zjy.bll.service.UserInfoService;
import com.zjy.entities.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by jyzeng on 2016/12/20.
 */
public class myJob {
    @Autowired
    private UserInfoService userInfoService;
    private int counter = 0;

    public void execute() {
        try {
            System.out.format("%1$tF %1$tT (第%2$d次执行), userInfoService == null: %3$s\n", new Date(), counter++, String.valueOf(userInfoService == null));
            List<UserInfo> list = userInfoService.query(new UserInfo());
            System.out.println("list: " + JSON.toJSONString(list));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
