package com.zjy.bll.dao;

import com.zjy.bll.common.BaseDao;
import com.zjy.bll.vo.UserInfoVo;
import com.zjy.entities.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author chahongjing
 * @create 2016-12-05 22:18
 */
@Repository
public interface UserInfoDao extends BaseDao<UserInfo> {//BaseMapper<UserInfo>

    UserInfoVo getByCode(String userCode);

    Map<String, BigDecimal> queryRepeatCount(@Param("userId") String userId, @Param("userCode") String userCode);

    int updateUserPassword(@Param("userId") String userId, @Param("password") String password);
}
