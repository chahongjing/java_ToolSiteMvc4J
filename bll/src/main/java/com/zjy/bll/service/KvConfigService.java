package com.zjy.bll.service;

import com.zjy.bll.basebean.PageBean;
import com.zjy.bll.request.KvConfigRequest;
import com.zjy.entities.KvConfig;
import com.zjy.entities.UserInfo;

public interface KvConfigService {
    int add(KvConfig config, UserInfo user);
    int update(KvConfig config, UserInfo user);
    KvConfig get(String id);
    int delete(String id, UserInfo user);
    void save(KvConfig config, UserInfo user);
    PageBean<KvConfig> queryPageList(KvConfigRequest request);
    void removeAllCache();
    KvConfig getByCache(String code);
    KvConfig getByCode(String code);
}
