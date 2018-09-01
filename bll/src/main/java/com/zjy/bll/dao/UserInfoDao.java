package com.zjy.bll.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zjy.bll.common.BaseDao;
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
public interface UserInfoDao extends BaseDao<UserInfo>, BaseMapper<UserInfo> {
    UserInfo getByCode(String userCode);

    Map<String, BigDecimal> queryRepeatCount(@Param("userId") String userId, @Param("userCode") String userCode);
}
