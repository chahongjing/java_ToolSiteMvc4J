package com.zjy.web.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.BaseResult;
import com.zjy.bll.request.RoleInfoRequest;
import com.zjy.bll.service.RoleInfoService;
import com.zjy.bll.vo.RelateCheckVo;
import com.zjy.bll.vo.RoleInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    public BaseResult<PageInfo> queryPageList(RoleInfoRequest request) {
        PageInfo<RoleInfoVo> pageInfo = (PageInfo<RoleInfoVo>) roleInfoSrv.queryPageList(request);
        return BaseResult.OK(pageInfo);
    }

    @RequestMapping("/getDetail")
    @ResponseBody
    public BaseResult<RoleInfoVo> getDetail(String id) {
        RoleInfoVo userInfo = roleInfoSrv.getVo(id);
        return BaseResult.OK(userInfo);
    }

    @RequestMapping("/save")
    @ResponseBody
    public BaseResult<String> save(RoleInfoVo vo) {
        roleInfoSrv.save(vo);
        return BaseResult.OK("");
    }

    @RequestMapping("/delete")
    @ResponseBody
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

    @RequestMapping("/getRolePermission")
    @ResponseBody
    public BaseResult<List<RelateCheckVo>> getRolePermission(String id) {
        List<RelateCheckVo> list = roleInfoSrv.getRolePermission(id);
        return BaseResult.OK(list);
    }

    @RequestMapping("/savePermission")
    @ResponseBody
    public BaseResult savePermission(String listStr) {
        List<RelateCheckVo> list = JSON.parseArray(listStr, RelateCheckVo.class);
        roleInfoSrv.savePermission(list);
        return BaseResult.OK();
    }
}
