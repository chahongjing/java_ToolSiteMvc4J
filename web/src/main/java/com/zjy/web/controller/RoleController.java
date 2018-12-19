package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.bll.baseBean.PageBean;
import com.zjy.bll.request.RoleInfoRequest;
import com.zjy.bll.service.RoleInfoService;
import com.zjy.bll.vo.RoleInfoVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/2/27.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleInfoService roleInfoSrv;

    @RequestMapping("/queryPageList")
    @ResponseBody
    @RequiresPermissions("roleList_enter")
    public BaseResult<PageBean> queryPageList(RoleInfoRequest request) {
        PageBean<RoleInfoVo> pageBean = (PageBean<RoleInfoVo>) roleInfoSrv.queryPageList(request);
        return BaseResult.OK(pageBean);
    }

    @RequestMapping("/getDetail")
    @ResponseBody
    @RequiresPermissions("roleEdit_enter")
    public BaseResult<RoleInfoVo> getDetail(String id) {
        RoleInfoVo userInfo = roleInfoSrv.getVo(id);
        return BaseResult.OK(userInfo);
    }

    @RequestMapping("/save")
    @ResponseBody
    @RequiresPermissions("roleEdit_save")
    public BaseResult<String> save(RoleInfoVo vo) {
        roleInfoSrv.save(vo);
        return BaseResult.OK("");
    }

    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions("roleList_delete")
    public BaseResult<String> delete(String id) {
        roleInfoSrv.delete(id);
        return BaseResult.OK("");
    }

    @RequestMapping("/list")
    public String tableToObject() {
        return "sys/menu";
    }

    @RequestMapping("/menuEdit")
    public String editMenu(String menuId, Model model) {
        model.addAttribute("menuId", menuId);
        return "sys/menuEdit";
    }
}
