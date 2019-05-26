package com.zjy.bll.dao;

import com.zjy.bll.common.BaseDao;
import com.zjy.bll.vo.OperLogVo;
import com.zjy.entities.OperLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperLogDao extends BaseDao<OperLog> {
    OperLogVo get(String logID);
    List<OperLogVo> query(OperLogVo entity);
    int deleteAll();
}
