package com.zjy.bll.dao;

import com.zjy.bll.common.BaseDao;
import com.zjy.bll.vo.RoleInfoVo;
import com.zjy.entities.RoleInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface RoleInfoDao extends BaseDao<RoleInfo> {
    Map<String, BigDecimal> queryRepeatCount(@Param("roleId") String roleId, @Param("code") String code);

    List<RoleInfoVo> queryAllRole();
}
