package com.zjy.bll.service;

import com.zjy.bll.vo.RelateCheckVo;
import com.zjy.bll.vo.UserRoleVo;

import java.util.List;

public interface UserRoleService {
    List<RelateCheckVo> queryAllRoleWithUserRole(String id);

    void saveUserRole(List<RelateCheckVo> list);

    List<UserRoleVo> queryListByUserId(String userId);

    List<String> queryUserRoleCodeList(String userId);
}
