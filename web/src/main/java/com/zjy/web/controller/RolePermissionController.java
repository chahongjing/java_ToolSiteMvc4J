package com.zjy.web.controller;

import com.alibaba.fastjson.JSON;
import com.zjy.baseframework.BaseResult;
import com.zjy.bll.service.RolePermissionService;
import com.zjy.bll.vo.RelateCheckVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/rolePermission")
@ResponseBody
public class RolePermissionController extends BaseController {

    @Autowired
    private RolePermissionService rolePermissionSrv;

    @RequestMapping("/getRolePermission")
    public BaseResult<List<RelateCheckVo>> getRolePermission(String id) {
        List<RelateCheckVo> list = rolePermissionSrv.getRolePermission(id);
        return BaseResult.OK(list);
    }

    @RequestMapping("/savePermission")
    public BaseResult savePermission(String listStr) {
        List<RelateCheckVo> list = JSON.parseArray(listStr, RelateCheckVo.class);
        rolePermissionSrv.savePermission(list);
        return BaseResult.OK();
    }
}
