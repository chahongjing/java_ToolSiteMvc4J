package com.zjy.bll.service;

import com.alibaba.fastjson.JSON;
import com.zjy.baseframework.JedisCacheHelper;
import com.zjy.baseframework.JedisHelper;
import com.zjy.baseframework.ServiceException;
import com.zjy.baseframework.interfaces.ICache;
import com.zjy.bll.basebean.PageBean;
import com.zjy.bll.common.BaseService;
import com.zjy.bll.dao.KvConfigDao;
import com.zjy.bll.request.KvConfigRequest;
import com.zjy.bll.request.MenuRequest;
import com.zjy.bll.vo.MenuVo;
import com.zjy.bll.vo.RolePermissionVo;
import com.zjy.bll.vo.UserRoleVo;
import com.zjy.entities.KvConfig;
import com.zjy.entities.Menu;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
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
        cache.hSet(KEY, config.getCode(), config.getValue());
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
        cache.hSet(KEY, config.getCode(), config.getValue());
        return update;
    }

    @Override
    public KvConfig get(String id) {
        return super.get(id);
    }

    @Override
    public KvConfig getByCache(String code) {
        String o = (String)cache.hGet(KEY, code);
        if(StringUtils.isNotBlank(o)) {
            return JSON.parseObject(o, KvConfig.class);
        }
        KvConfig byCode = this.getByCode(code);
        if(byCode != null) {
            cache.hSet(KEY, code, byCode.getValue());
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
            cache.hDelete(KEY, kvConfig.getCode());
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
        cache.hDelete(KEY);
    }

    @Override
    public PageBean<KvConfig> queryPageList(KvConfigRequest request) {
        KvConfig po = new KvConfig();
//        po.setName(request.getName());
        return (PageBean<KvConfig>) super.queryPageList(request, po);
    }

}
