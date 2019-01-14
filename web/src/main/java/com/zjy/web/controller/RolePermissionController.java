package com.zjy.web.controller;

import com.alibaba.fastjson.JSON;
import com.zjy.baseframework.BaseResult;
import com.zjy.bll.service.RolePermissionService;
import com.zjy.bll.vo.RelateCheckVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rolePermission")
public class RolePermissionController extends BaseController {

    @Autowired
    private RolePermissionService rolePermissionSrv;

    @RequestMapping("/getRolePermission")
    @RequiresPermissions("roleGrantPermission_enter")
    public BaseResult<List<RelateCheckVo>> getRolePermission(String id) {
        List<RelateCheckVo> list = rolePermissionSrv.getRolePermission(id);
        return BaseResult.ok(list);
    }

    @PostMapping("/savePermission")
    @RequiresPermissions("roleGrantPermission_enter")
    public BaseResult savePermission(String listStr) {
        List<RelateCheckVo> list = JSON.parseArray(listStr, RelateCheckVo.class);
        rolePermissionSrv.savePermission(list);
        return BaseResult.ok();
    }
}
