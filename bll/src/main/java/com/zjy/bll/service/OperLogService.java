package com.zjy.bll.service;

import com.zjy.bll.basebean.PageBean;
import com.zjy.bll.request.OperLogRequest;
import com.zjy.bll.vo.OperLogVo;
import com.zjy.entities.OperLog;

import java.util.List;

public interface OperLogService {
    OperLogVo get(String logId);
    PageBean<OperLogVo> queryPageList(OperLogRequest request);
    List<OperLogVo> queryList(OperLogVo vo);
    int delete(String id);
    int deleteAll();
}
