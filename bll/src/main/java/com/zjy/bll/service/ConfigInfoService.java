package com.zjy.bll.service;

import com.github.pagehelper.PageInfo;
import com.zjy.bll.request.ConfigInfoRequest;
import com.zjy.bll.vo.ConfigInfoVo;
import com.zjy.entities.ConfigInfo;

/**
 * Created by Administrator on 2018/11/1.
 */
public interface ConfigInfoService {
    int add(ConfigInfo po);

    int update(ConfigInfo po);

    int delete(String id);

    void save(ConfigInfoVo vo);

    ConfigInfoVo getVo(String id);

    PageInfo<? extends ConfigInfo> queryPageList(ConfigInfoRequest request);
}
