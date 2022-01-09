package com.zjy.bll.dao;

import com.zjy.bll.common.BaseDao;
import com.zjy.entities.KvConfigLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KvConfigLogDao extends BaseDao<KvConfigLog> {
    List<KvConfigLog> queryByCode(String code);
}
