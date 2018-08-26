package com.zjy.bll.service;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.ServiceException;
import com.zjy.bll.common.BaseService;
import com.zjy.bll.dao.MenuDao;
import com.zjy.bll.request.MenuRequest;
import com.zjy.bll.vo.MenuVo;
import com.zjy.entities.Menu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl extends BaseService<MenuDao, Menu> implements MenuService {
    /**
     * 添加用户
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public int add(Menu entity) {
        return super.add(entity);
    }

    /**
     * 修改用户
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public int update(Menu entity){
        return super.update(entity);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    @Transactional
    public int delete(String id) {
        return super.delete(id);
    }

    /**
     * 保存用户
     * @param menuInfo
     */
    @Override
    @Transactional
    public void saveMenu(MenuVo menuInfo) {
        MenuVo vo = getVo(menuInfo.getMenuId());
        beforeCheck(menuInfo);
        // 处理密码
        if(vo.getIsSave()) {
            update(menuInfo);
        } else {
            add(menuInfo);
        }
    }

    @Override
    public PageInfo<? extends Menu> queryPageList(MenuRequest request) {
        Menu menu = new Menu();
        menu.setName(request.getName());
        PageInfo<MenuVo> pageInfo = (PageInfo<MenuVo>)super.queryPageList(request, menu);
        return pageInfo;
    }

    public MenuVo get(String menuId) {
        return (MenuVo)super.get(menuId);
    }

    @Override
    public MenuVo getVo(String menuId) {
        MenuVo vo = get(menuId);
        if(vo == null) {
            vo = new MenuVo();
            vo.setMenuId(menuId);
            vo.setIsSave(false);
        } else {
            vo.setIsSave(true);
        }
        return vo;
    }

    protected void beforeCheck(MenuVo menu) {
        if(StringUtils.isBlank(menu.getName())) {
            throw new ServiceException("请输入功能名称！");
        }
        Map<String, BigDecimal> map = dao.queryRepeatCount(menu.getMenuId(), menu.getCode());
        if(map != null && map.containsKey("CODECOUNT") && map.get("CODECOUNT").intValue() > 0) {
            throw new ServiceException("功能名称重复！");
        }
    }

    public List<MenuVo> queryParentList() {
        return dao.queryParentList();
    }

    public List<MenuVo> queryAllMenu() {
        List<MenuVo> result = new ArrayList<>(), list = (List<MenuVo>)dao.query(null);
        List<MenuVo> parentList = list.stream().filter(item -> StringUtils.isBlank(item.getPId())).collect(Collectors.toList());
        List<MenuVo> children = list.stream().filter(item -> StringUtils.isNotBlank(item.getPId())).collect(Collectors.toList());
        for (MenuVo parent : parentList) {
            result.add(parent);
            List<MenuVo> temp = children.stream().filter(item -> item.getPId().equals(parent.getMenuId())).collect(Collectors.toList());
            result.addAll(temp);
        }
        return result;
    }
}
