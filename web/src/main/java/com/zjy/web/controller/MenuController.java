package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.bll.baseBean.PageBean;
import com.zjy.bll.request.MenuRequest;
import com.zjy.bll.service.MenuService;
import com.zjy.bll.vo.MenuVo;
import com.zjy.bll.vo.TreeNode;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/27.
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuSrv;

    @RequestMapping("/queryPageList")
    @ResponseBody
    @RequiresPermissions("menuList_enter")
    public BaseResult<PageBean> queryPageList(MenuRequest request) {
        PageBean<MenuVo> pageBean = (PageBean<MenuVo>) menuSrv.queryPageList(request);
        return BaseResult.OK(pageBean);
    }

    @RequestMapping("/getDetail")
    @ResponseBody
    @RequiresPermissions("menuEdit_enter")
    public BaseResult<MenuVo> getDetail(String id) {
        MenuVo userInfo = menuSrv.getVo(id);
        return BaseResult.OK(userInfo);
    }

    @RequestMapping("/save")
    @ResponseBody
    @RequiresPermissions("menuEdit_save")
    public BaseResult<String> save(MenuVo vo) {
        menuSrv.save(vo);
        return BaseResult.OK("");
    }

    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions("menuList_delete")
    public BaseResult<String> delete(String id) {
        menuSrv.delete(id);
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

    @RequestMapping("/queryMenu")
    @ResponseBody
    public BaseResult queryMenu() {
        List<MenuVo> list = menuSrv.queryPermissionMenu();
        List<TreeNode> nodeList = new ArrayList<>();
        TreeNode node;
        for (MenuVo menu : list) {
            node = new TreeNode();
            node.setId(menu.getMenuId());
            node.setPId(menu.getPId());
            node.setName(menu.getName());
            node.setSeq(menu.getSeq());
            node.setData(menu);
            nodeList.add(node);
        }

        return BaseResult.OK(nodeList);
    }

    @RequestMapping("/queryParentList")
    @ResponseBody
    @RequiresPermissions("menuEdit_enter")
    public BaseResult<List<MenuVo>> queryParentList() {
        List<MenuVo> list = menuSrv.queryParentList();
        return BaseResult.OK(list);
    }

    @RequestMapping("/queryPageMenuList")
    @ResponseBody
    @RequiresPermissions("menuList_enter")
    public BaseResult<List<MenuVo>> queryPageList() {
        List<MenuVo> list = menuSrv.queryPageMenuList();
        return BaseResult.OK(list);
    }
}
