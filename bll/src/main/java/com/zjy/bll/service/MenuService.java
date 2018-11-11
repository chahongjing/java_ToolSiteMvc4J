package com.zjy.bll.service;

import com.github.pagehelper.PageInfo;
import com.zjy.bll.request.MenuRequest;
import com.zjy.bll.vo.MenuVo;
import com.zjy.entities.Menu;

import java.util.List;

public interface MenuService {
    int add(Menu po);

    int update(Menu po);

    int delete(String id);

    void save(MenuVo vo);

    MenuVo getVo(String id);

    PageInfo<? extends Menu> queryPageList(MenuRequest request);

    List<MenuVo> queryParentList();

    List<MenuVo> queryPageMenuList();

    List<MenuVo> queryAllMenu();
}
