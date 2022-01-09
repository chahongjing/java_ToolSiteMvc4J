package com.zjy.bll.service;

import com.zjy.bll.basebean.PageBean;
import com.zjy.bll.request.KvConfigLogRequest;
import com.zjy.bll.vo.KvConfigLogVo;
import com.zjy.entities.KvConfig;
import com.zjy.entities.UserInfo;

public interface KvConfigLogService {
    int add(KvConfig config, UserInfo user);

    PageBean<KvConfigLogVo> queryPageById(KvConfigLogRequest request);
}
