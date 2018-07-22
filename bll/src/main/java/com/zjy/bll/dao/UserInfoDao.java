package com.zjy.bll.dao;

import com.zjy.bll.common.BaseDao;
import com.zjy.entities.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author chahongjing
 * @create 2016-12-05 22:18
 */
@Repository
public interface UserInfoDao extends BaseDao<UserInfo> {
    UserInfo getByUserCode(String userCode);
    List<UserInfo> test(@Param("a") String aa, @Param("b") UserInfo bb);

    Map<String, Long> queryRepeatCount(@Param("userGuid") String userGuid, @Param("userCode") String userCode);
}
