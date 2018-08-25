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
    public BaseResult queryMenu(String type, String url, String user, String password, String tableName) {
        List<MenuVo> list = menuSrv.queryAllMenu();
        List<ZTreeNode> nodeList = new ArrayList<>();
//        List<Menu> list = new ArrayList<>();
//        Menu menuTemp = new Menu();
//        menuTemp.setMenuId("1");
//        menuTemp.setPId("0");
//        menuTemp.setName("业务");
//        menuTemp.setUrl("/abc");
//        menuTemp.setSeq(0);
//        menuTemp.setIcon("fa-cog");
//        list.add(menuTemp);
//
//        menuTemp = new Menu();
//        menuTemp.setMenuId("3");
//        menuTemp.setPId("1");
//        menuTemp.setName("业务A");
//        menuTemp.setUrl("/abc");
//        menuTemp.setSeq(0);
//        menuTemp.setIcon("fa-cog");
//        list.add(menuTemp);
//
//        menuTemp = new Menu();
//        menuTemp.setMenuId("4");
//        menuTemp.setPId("1");
//        menuTemp.setName("业务B");
//        menuTemp.setUrl("/abc");
//        menuTemp.setSeq(1);
//        menuTemp.setIcon("fa-cog");
//        list.add(menuTemp);
//
//        menuTemp = new Menu();
//        menuTemp.setMenuId("2");
//        menuTemp.setPId("0");
//        menuTemp.setName("管理");
//        menuTemp.setUrl("/def");
//        menuTemp.setSeq(1);
//        menuTemp.setIcon("fa-cog");
//        list.add(menuTemp);
//
//        menuTemp = new Menu();
//        menuTemp.setMenuId("5");
//        menuTemp.setPId("2");
//        menuTemp.setName("工具");
//        menuTemp.setUrl("/abc");
//        menuTemp.setSeq(0);
//        menuTemp.setIcon("fa-cog");
//        list.add(menuTemp);
//
//        menuTemp = new Menu();
//        menuTemp.setMenuId("6");
//        menuTemp.setPId("2");
//        menuTemp.setName("日志");
//        menuTemp.setUrl("/abc");
//        menuTemp.setSeq(1);
//        menuTemp.setIcon("fa-cog");
//        list.add(menuTemp);
//
//        menuTemp = new Menu();
//        menuTemp.setMenuId("7");
//        menuTemp.setPId("2");
//        menuTemp.setName("用户管理");
//        menuTemp.setUrl("/userinfo/user");
//        menuTemp.setSeq(1);
//        menuTemp.setIcon("fa-cog");
//        list.add(menuTemp);
//
//        menuTemp = new Menu();
//        menuTemp.setMenuId("8");
//        menuTemp.setPId("2");
//        menuTemp.setName("角色管理");
//        menuTemp.setUrl("/sys/role");
//        menuTemp.setSeq(1);
//        menuTemp.setIcon("fa-cog");
//        list.add(menuTemp);
//
//        menuTemp = new Menu();
//        menuTemp.setMenuId("9");
//        menuTemp.setPId("2");
//        menuTemp.setName("功能管理");
//        menuTemp.setUrl("/menu/list");
//        menuTemp.setSeq(1);
//        menuTemp.setIcon("fa-cog");
//        list.add(menuTemp);
//
//        menuTemp = new Menu();
//        menuTemp.setMenuId("10");
//        menuTemp.setPId("2");
//        menuTemp.setName("权限管理");
//        menuTemp.setUrl("/sys/role");
//        menuTemp.setSeq(1);
//        menuTemp.setIcon("fa-cog");
//        list.add(menuTemp);

        ZTreeNode node;
        for (Menu menu : list) {
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


    @RequestMapping("/queryPageList")
    @ResponseBody
    public BaseResult<PageInfo> queryPageList(MenuRequest userInfo) {
        PageInfo<Menu> pageInfo = menuSrv.queryPageList(userInfo);
        return BaseResult.OK(pageInfo);
    }

    @RequestMapping("/getMenuInfo")
    @ResponseBody
    public BaseResult<MenuVo> getMenuInfo(String menuId) {
        MenuVo userInfo = menuSrv.getVo(menuId);
        return BaseResult.OK(userInfo);
    }

    @RequestMapping("/queryParentList")
    @ResponseBody
    public BaseResult<List<MenuVo>> queryParentList() {
        List<MenuVo> list = menuSrv.queryParentList();
        return BaseResult.OK(list);
    }

    @RequestMapping("/saveMenu")
    @ResponseBody
    public BaseResult<String> saveUser(MenuVo menu) {
        menuSrv.saveMenu(menu);
        return BaseResult.OK("");
    }
}
