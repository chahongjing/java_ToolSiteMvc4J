package com.zjy.bll.dao;

import com.zjy.bll.common.BaseDao;
import com.zjy.entities.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;

@Repository
public interface PermissionDao extends BaseDao<Permission> {
    Map<String, BigDecimal> queryRepeatCount(@Param("permissionId") String permissionId, @Param("code") String code);
}
