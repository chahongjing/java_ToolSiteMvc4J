package com.zjy.bll.dao;

import com.zjy.bll.common.BaseDao;
import com.zjy.entities.KvConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface KvConfigDao extends BaseDao<KvConfig> {
    KvConfig getByCode(String code);
}
