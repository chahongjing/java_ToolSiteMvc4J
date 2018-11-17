package com.zjy.bll.service;

import com.github.pagehelper.PageInfo;
import com.zjy.bll.request.RoleInfoRequest;
import com.zjy.bll.vo.RoleInfoVo;
import com.zjy.entities.RoleInfo;

import java.util.List;

public interface RoleInfoService {
    int add(RoleInfo po);

    int update(RoleInfo po);

    int delete(String id);

    void save(RoleInfoVo vo);

    RoleInfoVo getVo(String id);

    PageInfo<? extends RoleInfo> queryPageList(RoleInfoRequest request);

    List<RoleInfoVo> queryAllRole();
}
