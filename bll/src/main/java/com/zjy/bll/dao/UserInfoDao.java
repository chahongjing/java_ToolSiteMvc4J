package com.zjy.bll.dao;

import com.zjy.bll.common.BaseDao;
import com.zjy.entities.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chahongjing
 * @create 2016-12-05 22:18
 */
@Repository
public interface UserInfoDao extends BaseDao<UserInfo> {
    UserInfo getByUserCode(String userCode);
    List<UserInfo> test(@Param("a") String aa, @Param("b") UserInfo bb);
}
