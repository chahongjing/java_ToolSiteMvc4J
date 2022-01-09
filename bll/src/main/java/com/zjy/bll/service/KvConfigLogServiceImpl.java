package com.zjy.bll.service;

import com.zjy.bll.basebean.PageBean;
import com.zjy.bll.common.BaseService;
import com.zjy.bll.dao.KvConfigLogDao;
import com.zjy.bll.request.KvConfigLogRequest;
import com.zjy.bll.vo.KvConfigLogVo;
import com.zjy.bll.vo.UserInfoVo;
import com.zjy.entities.KvConfig;
import com.zjy.entities.KvConfigLog;
import com.zjy.entities.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class KvConfigLogServiceImpl extends BaseService<KvConfigLogDao, KvConfigLog> implements KvConfigLogService {

    @Autowired
    UserInfoService userInfoSrv;
    /**
     * 添加用户
     *
     * @param config
     * @return
     */
    @Override
    @Transactional
    public int add(KvConfig config, UserInfo user) {
        KvConfigLog log = new KvConfigLog();
        log.setKvId(config.getId());
        log.setCode(config.getCode());
        log.setValue(config.getValue());
        log.setCreateTime(new Date());
        log.setCreateBy(user.getUserId());
        int add = super.add(log);
        return add;
    }

    /**
     * 添加用户
     *
     * @param config
     * @return
     */
    @Override
    @Transactional
    public int add(KvConfigLog config) {
        int add = super.add(config);
        return add;
    }

    @Override
    public PageBean<KvConfigLogVo> queryPageById(KvConfigLogRequest request) {
        KvConfigLog po = new KvConfigLog();
        po.setKvId(request.getKvId());
        request.setOrderBy("createTime desc");
        PageBean<KvConfigLogVo> pageBean = (PageBean<KvConfigLogVo>)super.queryPageList(request, po);
        for (KvConfigLogVo kvConfigLogVo : pageBean.getList()) {
            UserInfoVo user = userInfoSrv.getVo(kvConfigLogVo.getCreateBy());
            if(user != null) {
                kvConfigLogVo.setCreateByName(user.getUserName());
            }
        }
        return pageBean;
    }
}
