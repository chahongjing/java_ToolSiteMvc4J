package com.zjy.bll.service;

import com.zjy.baseframework.BaseResult;
import com.zjy.entities.UserInfo;

/**
 * @author chahongjing
 * @create 2016-12-05 22:16
 */
public interface UserInfoService {
    BaseResult<String> login(UserInfo user);
}
