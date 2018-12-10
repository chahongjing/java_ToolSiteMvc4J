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
    int add(UserInfo po);

    int update(UserInfo po);

    int delete(String id);

    void save(UserInfoVo vo);

    UserInfoVo getVo(String id);

    PageInfo<? extends UserInfo> queryPageList(UserInfoRequest request);

    BaseResult<UserInfoVo> login(UserInfo user);

    BaseResult<String> logout();

    List<UserInfo> query(UserInfo user);

    UserInfoVo getByUserCode(String userCode);

    void changePassword(String userCode, String oldPassword, String newPassword);

    void resetPassword(String userCode, String password);
}
