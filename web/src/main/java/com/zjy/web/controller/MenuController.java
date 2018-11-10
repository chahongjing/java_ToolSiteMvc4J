package com.zjy.web.controller;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.BaseResult;
import com.zjy.bll.request.MenuRequest;
import com.zjy.bll.service.MenuService;
import com.zjy.bll.vo.MenuVo;
import com.zjy.bll.vo.ZTreeNode;
import com.zjy.entities.Menu;
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
    public BaseResult<PageInfo> queryPageList(MenuRequest userInfo) {
        PageInfo<Menu> pageInfo = menuSrv.queryPageList(userInfo);
        return BaseResult.OK(pageInfo);
    }

    @RequestMapping("/getDetail")
    @ResponseBody
    public BaseResult<MenuVo> getDetail(String id) {
        MenuVo userInfo = menuSrv.getVo(id);
        return BaseResult.OK(userInfo);
    }

    @RequestMapping("/save")
    @ResponseBody
    public BaseResult<String> save(MenuVo menu) {
        menuSrv.saveMenu(menu);
        return BaseResult.OK("");
    }

    @RequestMapping("/delete")
    @ResponseBody
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
        List<MenuVo> list = menuSrv.queryAllMenu();
        List<ZTreeNode> nodeList = new ArrayList<>();
        ZTreeNode node;
        for (MenuVo menu : list) {
            node = new ZTreeNode();
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
    public BaseResult<List<MenuVo>> queryParentList() {
        List<MenuVo> list = menuSrv.queryParentList();
        return BaseResult.OK(list);
    }
}
