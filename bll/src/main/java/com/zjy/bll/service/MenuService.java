package com.zjy.bll.service;

import com.zjy.bll.baseBean.PageBean;
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

    PageBean<MenuVo> queryPageList(MenuRequest request);

    List<MenuVo> queryParentList();

    List<MenuVo> queryPageMenuList();

    List<MenuVo> queryPermissionMenu();

    List<MenuVo> queryAllMenu();
}
