package com.zjy.bll.dao;

import com.zjy.bll.common.BaseDao;
import com.zjy.bll.vo.RoleInfoVo;
import com.zjy.entities.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleDao extends BaseDao<UserRole> {

    int add(UserRole entity);

    int deleteEntity(UserRole entity);

    List<RoleInfoVo> queryListByUserId(@Param("userId") String userId);
}
