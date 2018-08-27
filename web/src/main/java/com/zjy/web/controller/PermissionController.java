package com.zjy.web.controller;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.BaseResult;
import com.zjy.bll.request.PermissionRequest;
import com.zjy.bll.service.MenuService;
import com.zjy.bll.service.PermissionService;
import com.zjy.bll.vo.MenuVo;
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

    @Autowired
    private MenuService menuSrv;

        @RequestMapping("/list")
        public String list(String menuId, Model model) {
            model.addAttribute("menuId", menuId);
            return "sys/permission";
        }

        @RequestMapping("/permissionEdit")
        public String editPermission(String permissionId, String menuId, Model model) {
            model.addAttribute("permissionId", permissionId);
            model.addAttribute("menuId", menuId);
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
        public BaseResult<PermissionVo> getPermissionInfo(String permissionId, String menuId) {
            PermissionVo permissionVo = permissionSrv.getVo(permissionId);
            if(!permissionVo.getIsSave()) {
                permissionVo.setMenuId(menuId);
                MenuVo menu = menuSrv.getVo(menuId);
                permissionVo.setMenuName(menu.getName());
            }
            return BaseResult.OK(permissionVo);
        }

        @RequestMapping("/savePermission")
        @ResponseBody
        public BaseResult<String> saveUser(PermissionVo permission) {
            permissionSrv.savePermission(permission);
            return BaseResult.OK("");
        }

    @RequestMapping("/delete")
    @ResponseBody
    public BaseResult<String> delete(String permissionId) {
        permissionSrv.delete(permissionId);
        return BaseResult.OK("");
    }
}
