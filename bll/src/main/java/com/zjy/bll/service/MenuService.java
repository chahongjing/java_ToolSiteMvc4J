package com.zjy.bll.service;

import com.github.pagehelper.PageInfo;
import com.zjy.bll.request.MenuRequest;
import com.zjy.bll.vo.MenuVo;
import com.zjy.entities.Menu;

import java.util.List;

public interface MenuService {
    int add(Menu menu);
    int update(Menu menu);
    int delete(String menuId);
    void saveMenu(MenuVo userInfo);
    MenuVo getVo(String menuId);
    PageInfo queryPageList(MenuRequest request);

    List<MenuVo> queryParentList();

    List<MenuVo> queryAllMenu();
}
