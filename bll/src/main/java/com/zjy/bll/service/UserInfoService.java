package com.zjy.bll.service;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.BaseResult;
import com.zjy.bll.request.UserInfoRequest;
import com.zjy.bll.vo.UserInfoVo;
import com.zjy.entities.UserInfo;

import java.util.List;

/**
 * @author chahongjing
 * @create 2016-12-05 22:16
 */
public interface UserInfoService {
    UserInfoVo getVo(String userGuid);

    void saveUser(UserInfoVo userInfo);

    BaseResult<String> login(UserInfo user);

    BaseResult<String> logout();

    List<UserInfo> query(UserInfo user);

    PageInfo queryPageList(UserInfoRequest request);

    UserInfo get(String id);

    UserInfo getByUserCode(String userCode);

    int add(UserInfo entity);

    int delete(String id);
}
