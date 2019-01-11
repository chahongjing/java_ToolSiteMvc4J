package com.zjy.bll.service;

import com.zjy.baseframework.ServiceException;
import com.zjy.bll.baseBean.PageBean;
import com.zjy.bll.common.BaseService;
import com.zjy.bll.dao.ConfigInfoDao;
import com.zjy.bll.request.ConfigInfoRequest;
import com.zjy.bll.vo.ConfigInfoVo;
import com.zjy.entities.ConfigInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Administrator on 2018/11/1.
 */
@Service
public class ConfigInfoServiceImpl extends BaseService<ConfigInfoDao, ConfigInfo> implements ConfigInfoService {

    @Override
    public int add(ConfigInfo vo) {
        vo.setCreatedBy(shiroRealm.getCurrentUser().getUserId());
        vo.setCreatedOn(new Date());
        return super.add(vo);
    }

    @Override
    public int update(ConfigInfo vo) {
        vo.setModifiedBy(shiroRealm.getCurrentUser().getUserId());
        vo.setModifiedOn(new Date());
        return super.update(vo);
    }

    @Override
    public PageBean<? extends ConfigInfo> queryPageList(ConfigInfoRequest request) {
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setName(request.getName());
        return super.queryPageList(request, configInfo);
    }

    @Override
    public ConfigInfoVo getVo(String id) {
        ConfigInfoVo vo = (ConfigInfoVo) get(id);
        if (vo == null) {
            vo = new ConfigInfoVo();
            vo.setId(id);
            vo.setIsSave(false);
        } else {
            vo.setIsSave(true);
        }
        return vo;
    }

    @Override
    @Transactional
    public void save(ConfigInfoVo vo) {
        ConfigInfoVo voDb = getVo(vo.getId());
        beforeCheck(vo);
        // 处理密码
        if (voDb.getIsSave()) {
            this.update(vo);
        } else {
            this.add(vo);
        }
    }

    protected void beforeCheck(ConfigInfoVo configInfo) {
        if (StringUtils.isBlank(configInfo.getName())) {
            throw new ServiceException("请输入功能名称！");
        }
    }
}
