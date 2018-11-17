package com.zjy.bll.service;

import com.zjy.bll.vo.RelateCheckVo;
import com.zjy.bll.vo.RolePermissionVo;

import java.util.List;

public interface RolePermissionService {

    List<RelateCheckVo> getRolePermission(String id);

    void savePermission(List<RelateCheckVo> list);

    List<RolePermissionVo> queryRolePermission(String roleId);

    List<RolePermissionVo> queryRolePermission(List<String> roleIdList);
}
