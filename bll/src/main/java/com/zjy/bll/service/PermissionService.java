package com.zjy.bll.service;

import com.github.pagehelper.PageInfo;
import com.zjy.bll.request.PermissionRequest;
import com.zjy.bll.vo.PermissionVo;
import com.zjy.entities.Permission;

import java.util.List;

public interface PermissionService {
    int add(Permission po);

    int update(Permission po);

    int delete(String id);

    void save(PermissionVo vo);

    PermissionVo getVo(String id);

    PageInfo<? extends Permission> queryPageList(PermissionRequest request);

    List<PermissionVo> queryAllPermissionList();
}
