package com.zjy.bll.dao;

import com.zjy.bll.common.BaseDao;
import com.zjy.bll.vo.RolePermissionVo;
import com.zjy.entities.RolePermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionDao  extends BaseDao<RolePermission> {

    int add(RolePermission entity);

    int deleteEntity(RolePermission entity);

    List<RolePermissionVo> queryByRoleList(@Param("roleIdList") List<String> roleIdList);

    int deleteByRoleId(@Param("roleId")String roleId);

    int deleteByPermissionId(@Param("permissionId")String permissionId);
}
