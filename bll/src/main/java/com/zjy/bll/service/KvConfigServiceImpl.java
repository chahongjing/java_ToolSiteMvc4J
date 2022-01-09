package com.zjy.bll.service;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.CacheLoader;
import com.zjy.baseframework.CacheFromGuavaHelper;
import com.zjy.baseframework.interfaces.ICache;
import com.zjy.bll.basebean.PageBean;
import com.zjy.bll.common.BaseService;
import com.zjy.bll.dao.KvConfigDao;
import com.zjy.bll.request.KvConfigRequest;
import com.zjy.entities.KvConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Map;

@Service("kvConfigServiceImpl")
public class KvConfigServiceImpl extends BaseService<KvConfigDao, KvConfig> implements KvConfigService {
    @Autowired
    private ICache cache;
    private final static String KEY = "kvconfig";

    /**
     * 添加用户
     *
     * @param config
     * @return
     */
    @Override
    @Transactional
    public int add(KvConfig config) {
        int add = super.add(config);
        cache.set(getHKey(KEY, config.getCode()), config.getValue());
        return add;
    }

    /**
     * 修改用户
     *
     * @param config
     * @return
     */
    @Override
    @Transactional
    public int update(KvConfig config) {
        int update = super.update(config);
        cache.set(getHKey(KEY, config.getCode()), config.getValue());
        return update;
    }

    @Override
    public KvConfig get(String id) {
        KvConfig kvConfig = super.get(id);
//        cache.get(getHKey(KEY, kvConfig.getCode()));
        return kvConfig;
    }

    @Override
    public KvConfig getByCache(String code) {
        String o = (String)cache.get(getHKey(KEY, code));
        if(StringUtils.isNotBlank(o)) {
            return JSON.parseObject(o, KvConfig.class);
        }
        KvConfig byCode = this.getByCode(code);
        if(byCode != null) {
            cache.set(getHKey(KEY, code), byCode.getValue());
        }
        return byCode;
    }

    @Override
    public KvConfig getByCode(String code) {
        return dao.getByCode(code);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public int delete(String id) {
        KvConfig kvConfig = this.get(id);
        if(kvConfig != null) {
            int delete = super.delete(id);
            cache.delete(getHKey(KEY, kvConfig.getCode()));
            return delete;
        }
        return -1;
    }

    /**
     * 保存用户
     *
     * @param config
     */
    @Transactional
    public void save(KvConfig config) {
        KvConfig voDb = this.get(config.getId());
//        beforeCheck(config);
        // 处理密码
        if (voDb != null) {
            update(config);
        } else {
            config.setCreateTime(new Date());
            add(config);
        }
    }

    @Override
    public void removeAllCache() {
        cache.getAll(KEY).forEach((key, value) -> cache.delete(key));
    }

    @Override
    public PageBean<KvConfig> queryPageList(KvConfigRequest request) {
        KvConfig po = new KvConfig();
//        po.setName(request.getName());
        return (PageBean<KvConfig>) super.queryPageList(request, po);
    }

    private String getHKey(String key, String field) {
        return String.format("%s:%s", key, field);
    }
}
