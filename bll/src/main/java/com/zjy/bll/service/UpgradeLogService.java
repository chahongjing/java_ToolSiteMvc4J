package com.zjy.bll.service;

import com.zjy.bll.basebean.PageBean;
import com.zjy.bll.request.UpgradeLogRequest;
import com.zjy.bll.vo.UpgradeLogVo;
import com.zjy.entities.UpgradeLog;

import java.util.List;

public interface UpgradeLogService {
    UpgradeLog get(String id);
    int delete(String id);
    void save(UpgradeLog config);
    PageBean<UpgradeLogVo> queryPageList(UpgradeLogRequest request);
    List<UpgradeLogVo> queryList(UpgradeLog log);
}
