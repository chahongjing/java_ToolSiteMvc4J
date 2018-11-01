package com.zjy.bll.service;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.ServiceException;
import com.zjy.bll.common.BaseService;
import com.zjy.bll.dao.ConfigInfoDao;
import com.zjy.bll.request.ConfigInfoRequest;
import com.zjy.bll.vo.ConfigInfoVo;
import com.zjy.entities.ConfigInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2018/11/1.
 */
@Service
public class ConfigInfoServiceImpl extends BaseService<ConfigInfoDao, ConfigInfo> implements ConfigInfoService {
    @Override
    public PageInfo<? extends ConfigInfo> queryPageList(ConfigInfoRequest request) {
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setName(request.getName());
        PageInfo<ConfigInfoVo> pageInfo = (PageInfo<ConfigInfoVo>) super.queryPageList(request, configInfo);
        return pageInfo;
    }

    @Override
    public ConfigInfoVo getVo(String configInfoId) {
        ConfigInfoVo vo = (ConfigInfoVo) get(configInfoId);
        if (vo == null) {
            vo = new ConfigInfoVo();
            vo.setId(configInfoId);
            vo.setIsSave(false);
        } else {
            vo.setIsSave(true);
        }
        return vo;
    }

    @Override
    @Transactional
    public void saveConfigInfo(ConfigInfoVo configInfo) {
        ConfigInfoVo vo = getVo(configInfo.getId());
        beforeCheck(configInfo);
        // 处理密码
        if (vo.getIsSave()) {
            update(configInfo);
        } else {
            add(configInfo);
        }
    }

    protected void beforeCheck(ConfigInfoVo configInfo) {
        if (StringUtils.isBlank(configInfo.getName())) {
            throw new ServiceException("请输入功能名称！");
        }
    }
}
