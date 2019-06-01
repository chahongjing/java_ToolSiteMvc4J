package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.bll.basebean.PageBean;
import com.zjy.bll.request.PermissionRequest;
import com.zjy.bll.service.FunctionInfoService;
import com.zjy.bll.service.PermissionService;
import com.zjy.bll.vo.FunctionInfoVo;
import com.zjy.bll.vo.PermissionVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("permission")
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionSrv;

    @Autowired
    private FunctionInfoService functionInfoSrv;

    @RequestMapping("queryPageList")
    @RequiresPermissions("permissionList_enter")
    public BaseResult<PageBean<PermissionVo>> queryPageList(PermissionRequest request) {
        PageBean<PermissionVo> pageBean = (PageBean<PermissionVo>) permissionSrv.queryPageList(request);
        return BaseResult.ok(pageBean);
    }

    @RequestMapping("getDetail")
    @RequiresPermissions("permissionEdit_enter")
    public BaseResult<PermissionVo> getDetail(String id, String functionId) {
        PermissionVo permissionVo = permissionSrv.getVo(id);
        if (!permissionVo.getIsSave()) {
            permissionVo.setFunctionId(functionId);
            FunctionInfoVo functionInfo = functionInfoSrv.getVo(functionId);
            permissionVo.setFunctionName(functionInfo.getName());
        }
        return BaseResult.ok(permissionVo);
    }

    @PostMapping("save")
    @RequiresPermissions("permissionEdit_save")
    public BaseResult<String> save(PermissionVo vo) {
        permissionSrv.save(vo);
        return BaseResult.ok();
    }

    @RequestMapping("delete")
    @RequiresPermissions("permissionList_delete")
    public BaseResult<String> delete(String id) {
        permissionSrv.delete(id);
        return BaseResult.ok();
    }
}
