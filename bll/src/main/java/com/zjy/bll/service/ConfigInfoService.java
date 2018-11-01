package com.zjy.bll.service;

import com.github.pagehelper.PageInfo;
import com.zjy.bll.request.ConfigInfoRequest;
import com.zjy.bll.vo.ConfigInfoVo;
import com.zjy.entities.ConfigInfo;

/**
 * Created by Administrator on 2018/11/1.
 */
public interface ConfigInfoService {
    PageInfo<? extends ConfigInfo> queryPageList(ConfigInfoRequest request);

    ConfigInfoVo getVo(String configInfoId);

    void saveConfigInfo(ConfigInfoVo configInfo);

    int delete(String id);
}
