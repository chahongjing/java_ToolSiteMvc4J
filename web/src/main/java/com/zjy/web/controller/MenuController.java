package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.bll.basebean.PageBean;
import com.zjy.bll.request.MenuRequest;
import com.zjy.bll.service.MenuService;
import com.zjy.bll.vo.MenuVo;
import com.zjy.bll.vo.TreeNode;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/27.
 */
@RestController
@RequestMapping("menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuSrv;

    @RequestMapping("queryPageList")
    @RequiresPermissions("menuList_enter")
    public BaseResult<PageBean> queryPageList(MenuRequest request) {
        PageBean<MenuVo> pageBean = menuSrv.queryPageList(request);
        return BaseResult.ok(pageBean);
    }

    @RequestMapping("getDetail")
    @RequiresPermissions("menuEdit_enter")
    public BaseResult<MenuVo> getDetail(String id) {
        MenuVo userInfo = menuSrv.getVo(id);
        return BaseResult.ok(userInfo);
    }

    @PostMapping("save")
    @RequiresPermissions("menuEdit_save")
    public BaseResult<String> save(MenuVo vo) {
        menuSrv.save(vo);
        return BaseResult.ok();
    }

    @RequestMapping("delete")
    @RequiresPermissions("menuList_delete")
    public BaseResult<String> delete(String id) {
        menuSrv.delete(id);
        return BaseResult.ok();
    }

    @RequestMapping("queryMenu")
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

        return BaseResult.ok(nodeList);
    }

    @RequestMapping("queryParentList")
    @RequiresPermissions("menuEdit_enter")
    public BaseResult<List<MenuVo>> queryParentList() {
        List<MenuVo> list = menuSrv.queryParentList();
        return BaseResult.ok(list);
    }

    @RequestMapping("queryPageMenuList")
    @RequiresPermissions("menuList_enter")
    public BaseResult<List<MenuVo>> queryPageList() {
        List<MenuVo> list = menuSrv.queryPageMenuList();
        return BaseResult.ok(list);
    }
}
