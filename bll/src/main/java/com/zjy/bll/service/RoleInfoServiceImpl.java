package com.zjy.bll.service;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.ServiceException;
import com.zjy.bll.common.BaseService;
import com.zjy.bll.dao.RoleInfoDao;
import com.zjy.bll.request.RoleInfoRequest;
import com.zjy.bll.vo.*;
import com.zjy.entities.RoleInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleInfoServiceImpl extends BaseService<RoleInfoDao, RoleInfo> implements RoleInfoService {

    @Autowired
    protected MenuService menuSrv;

    @Autowired
    protected FunctionInfoService functionInfoSrv;

    @Autowired
    protected PermissionService permissionSrv;

    /**
     * 添加用户
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public int add(RoleInfo entity) {
        return super.add(entity);
    }

    /**
     * 修改用户
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public int update(RoleInfo entity) {
        return super.update(entity);
    }

    /**
     * 删除用户
     *
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
     *
     * @param vo
     */
    @Override
    @Transactional
    public void save(RoleInfoVo vo) {
        RoleInfoVo voDb = getVo(vo.getRoleId());
        beforeCheck(vo);
        // 处理密码
        if (voDb.getIsSave()) {
            update(vo);
        } else {
            add(vo);
        }
    }

    @Override
    public PageInfo<? extends RoleInfo> queryPageList(RoleInfoRequest request) {
        RoleInfo po = new RoleInfo();
        po.setName(request.getName());
        PageInfo<RoleInfoVo> pageInfo = (PageInfo<RoleInfoVo>) super.queryPageList(request, po);
        return pageInfo;
    }

    public RoleInfoVo get(String id) {
        return (RoleInfoVo) super.get(id);
    }

    @Override
    public RoleInfoVo getVo(String id) {
        RoleInfoVo vo = get(id);
        if (vo == null) {
            vo = new RoleInfoVo();
            vo.setRoleId(id);
            vo.setIsSave(false);
        } else {
            vo.setIsSave(true);
        }
        return vo;
    }

    @Override
    public List<RelateCheckVo> getRolePermission(String id) {
        List<RelateCheckVo> list = new ArrayList<>();
        // 获取所有一级菜单和二级菜单
        List<MenuVo> menuVos = menuSrv.queryAllMenu();
        // 获取菜单下所有页面
        List<FunctionInfoVo> functionInfoVos = functionInfoSrv.queryAllFunctionList();
        // 获取页面下所有权限
        List<PermissionVo> permissionVos = permissionSrv.queryAllPermissionList();
        // 获取角色下的权限
        // 组织数据
        RelateCheckVo firtMenu, secondMenu, functionItem, permissionItem;
        // 一级
        for (MenuVo menuVo : menuVos) {
            if(menuVo.getPId() != null) continue;
            firtMenu = new RelateCheckVo();
            firtMenu.setId(id);
            firtMenu.setRelativeId(menuVo.getMenuId());
            firtMenu.setName(menuVo.getName());
            firtMenu.setIsCheck(false);
            if(list.size() == 0) firtMenu.setShowDetail(true);
            list.add(firtMenu);
            // 二级
            List<MenuVo> children = menuVos.stream().filter(item -> menuVo.getMenuId().equalsIgnoreCase(item.getPId())).collect(Collectors.toList());
            if(CollectionUtils.isEmpty(children)) continue;
            for (MenuVo child : children) {
                secondMenu = new RelateCheckVo();
                secondMenu.setId(id);
                secondMenu.setRelativeId(child.getMenuId());
                secondMenu.setName(child.getName());
                secondMenu.setIsCheck(false);
                if(firtMenu.getSubList().size() == 0) secondMenu.setShowDetail(true);
                firtMenu.getSubList().add(secondMenu);
                // 功能
                List<FunctionInfoVo> functions = functionInfoVos.stream().filter(item -> child.getMenuId().equals(item.getMenuId())).collect(Collectors.toList());
                if(CollectionUtils.isEmpty(functions)) continue;
                for (FunctionInfoVo function : functions) {
                    functionItem = new RelateCheckVo();
                    functionItem.setId(id);
                    functionItem.setRelativeId(function.getFunctionId());
                    functionItem.setName(function.getName());
                    functionItem.setIsCheck(false);
                    if(secondMenu.getSubList().size() == 0) functionItem.setShowDetail(true);
                    secondMenu.getSubList().add(functionItem);
                    // 权限
                    List<PermissionVo> permissions = permissionVos.stream().filter(item -> function.getFunctionId().equals(item.getFunctionId())).collect(Collectors.toList());
                    if(CollectionUtils.isEmpty(permissions)) continue;
                    for (PermissionVo permission : permissions) {
                        permissionItem = new RelateCheckVo();
                        permissionItem.setId(id);
                        permissionItem.setRelativeId(permission.getPermissionId());
                        permissionItem.setName(permission.getName());
                        permissionItem.setIsCheck(false);
                        if(functionItem.getSubList().size() == 0) permissionItem.setShowDetail(true);
                        functionItem.getSubList().add(permissionItem);
                    }
                }
            }
        }
        return list;
    }

    protected void beforeCheck(RoleInfoVo po) {
        if (StringUtils.isBlank(po.getName())) {
            throw new ServiceException("请输入功能名称！");
        }
        Map<String, BigDecimal> map = dao.queryRepeatCount(po.getRoleId(), po.getCode());
        if (map != null && map.containsKey("CODECOUNT") && map.get("CODECOUNT").intValue() > 0) {
            throw new ServiceException("功能名称重复！");
        }
    }
}
