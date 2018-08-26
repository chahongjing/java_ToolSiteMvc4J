package com.zjy.web.controller;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.BaseResult;
import com.zjy.bll.request.PermissionRequest;
import com.zjy.bll.service.PermissionService;
import com.zjy.bll.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController {

        @Autowired
        private PermissionService permissionSrv;

        @RequestMapping("/list")
        public String list(String menuId, Model model) {
            model.addAttribute("menuId", menuId);
            return "sys/permission";
        }

        @RequestMapping("/permissionEdit")
        public String editPermission(String permissionId, Model model) {
            model.addAttribute("permissionId", permissionId);
            return "sys/permissionEdit";
        }

        @RequestMapping("/queryPageList")
        @ResponseBody
        public BaseResult<PageInfo<PermissionVo>> queryPageList(PermissionRequest permissionInfo) {
            PageInfo<PermissionVo> pageInfo = permissionSrv.queryPageList(permissionInfo);
            return BaseResult.OK(pageInfo);
        }

        @RequestMapping("/getPermissionInfo")
        @ResponseBody
        public BaseResult<PermissionVo> getPermissionInfo(String permissionId) {
            PermissionVo userInfo = permissionSrv.getVo(permissionId);
            return BaseResult.OK(userInfo);
        }

        @RequestMapping("/savePermission")
        @ResponseBody
        public BaseResult<String> saveUser(PermissionVo permission) {
            permissionSrv.savePermission(permission);
            return BaseResult.OK("");
        }
    }
