package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.bll.basebean.PageBean;
import com.zjy.bll.request.RoleInfoRequest;
import com.zjy.bll.service.RoleInfoService;
import com.zjy.bll.vo.RoleInfoVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/2/27.
 */
@RestController
@RequestMapping("role")
public class RoleController extends BaseController {

    @Autowired
    private RoleInfoService roleInfoSrv;

    @RequestMapping("queryPageList")
    @RequiresPermissions("roleList_enter")
    public BaseResult<PageBean> queryPageList(RoleInfoRequest request) {
        PageBean<RoleInfoVo> pageBean = roleInfoSrv.queryPageList(request);
        return BaseResult.ok(pageBean);
    }

    @RequestMapping("getDetail")
    @RequiresPermissions("roleEdit_enter")
    public BaseResult<RoleInfoVo> getDetail(String id) {
        RoleInfoVo userInfo = roleInfoSrv.getVo(id);
        return BaseResult.ok(userInfo);
    }

    @PostMapping("save")
    @RequiresPermissions("roleEdit_save")
    public BaseResult<String> save(RoleInfoVo vo) {
        roleInfoSrv.save(vo);
        return BaseResult.ok();
    }

    @RequestMapping("delete")
    @RequiresPermissions("roleList_delete")
    public BaseResult<String> delete(String id) {
        roleInfoSrv.delete(id);
        return BaseResult.ok();
    }
}
