package com.zjy.bll.service;

import com.github.pagehelper.PageInfo;
import com.zjy.bll.request.PermissionRequest;
import com.zjy.bll.vo.PermissionVo;

public interface PermissionService {
    PageInfo<PermissionVo> queryPageList(PermissionRequest request);
    PermissionVo get(String permissionId);
    PermissionVo getVo(String permissionId);
    void savePermission(PermissionVo permissionInfo);
}
