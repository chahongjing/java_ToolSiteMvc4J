package com.zjy.bll.service;

import com.zjy.bll.basebean.PageBean;
import com.zjy.bll.request.KvConfigRequest;
import com.zjy.entities.KvConfig;

public interface KvConfigService {
    KvConfig get(String id);
    int delete(String id);
    void save(KvConfig config);
    PageBean<KvConfig> queryPageList(KvConfigRequest request);
    void removeAllCache();
    KvConfig getByCache(String code);
    KvConfig getByCode(String code);
}
