package com.zjy.bll.service;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.BaseResult;
import com.zjy.bll.request.UserInfoRequest;
import com.zjy.entities.UserInfo;

import java.util.List;

/**
 * @author chahongjing
 * @create 2016-12-05 22:16
 */
public interface UserInfoService {
    BaseResult<String> login(UserInfo user);

    List<UserInfo> query(UserInfo user);

    PageInfo queryPage(UserInfoRequest request);
}
