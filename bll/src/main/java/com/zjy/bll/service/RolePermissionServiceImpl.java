package com.zjy.bll.service;

import com.zjy.bll.common.BaseService;
import com.zjy.bll.dao.RolePermissionDao;
import com.zjy.bll.vo.*;
import com.zjy.entities.RolePermission;
import com.zjy.entities.enums.PermissionType;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RolePermissionServiceImpl extends BaseService<RolePermissionDao, RolePermission> implements RolePermissionService {

    @Autowired
    protected MenuService menuSrv;

    @Autowired
    protected FunctionInfoService functionInfoSrv;

    @Autowired
    protected PermissionService permissionSrv;

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
        List<RolePermissionVo> rolePermissionDbList = this.queryRolePermission(id);
        // 组织数据
        RelateCheckVo firtMenu, secondMenu, functionItem, permissionItem;
        // 一级
        for (MenuVo menuVo : menuVos) {
            if (menuVo.getPId() != null) continue;
            firtMenu = new RelateCheckVo();
            firtMenu.setId(id);
            firtMenu.setRelativeId(menuVo.getMenuId());
            firtMenu.setName(menuVo.getName());
            firtMenu.setType(PermissionType.FirstMenu);
            if (rolePermissionDbList.stream().anyMatch(item -> item.getRoleId().equals(id) && item.getPermissionId().equals(menuVo.getMenuId()))) {
                firtMenu.setSingleCheck(true);
            } else {
                firtMenu.setSingleCheck(false);
            }
            if (list.size() == 0 || true) firtMenu.setShowDetail(true);
            list.add(firtMenu);
            // 二级
            List<MenuVo> children = menuVos.stream().filter(item -> menuVo.getMenuId().equalsIgnoreCase(item.getPId())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(children)) continue;
            for (MenuVo child : children) {
                secondMenu = new RelateCheckVo();
                secondMenu.setId(id);
                secondMenu.setRelativeId(child.getMenuId());
                secondMenu.setName(child.getName());
                secondMenu.setType(PermissionType.SecondMenu);
                if (rolePermissionDbList.stream().anyMatch(item -> item.getRoleId().equals(id) && item.getPermissionId().equals(child.getMenuId()))) {
                    secondMenu.setSingleCheck(true);
                } else {
                    secondMenu.setSingleCheck(false);
                }
                if (firtMenu.getSubList().size() == 0 || true) secondMenu.setShowDetail(true);
                firtMenu.getSubList().add(secondMenu);
                // 功能
                List<FunctionInfoVo> functions = functionInfoVos.stream().filter(item -> child.getMenuId().equals(item.getMenuId())).collect(Collectors.toList());
                if (CollectionUtils.isEmpty(functions)) continue;
                for (FunctionInfoVo function : functions) {
                    functionItem = new RelateCheckVo();
                    functionItem.setId(id);
                    functionItem.setRelativeId(function.getFunctionId());
                    functionItem.setName(function.getName());
                    functionItem.setType(PermissionType.FunctionItem);
                    if (rolePermissionDbList.stream().anyMatch(item -> item.getRoleId().equals(id) && item.getPermissionId().equals(function.getFunctionId()))) {
                        functionItem.setSingleCheck(true);
                    } else {
                        functionItem.setSingleCheck(false);
                    }
                    if (secondMenu.getSubList().size() == 0 || true) functionItem.setShowDetail(true);
                    secondMenu.getSubList().add(functionItem);
                    // 权限
                    List<PermissionVo> permissions = permissionVos.stream().filter(item -> function.getFunctionId().equals(item.getFunctionId())).collect(Collectors.toList());
                    if (CollectionUtils.isEmpty(permissions)) continue;
                    for (PermissionVo permission : permissions) {
                        permissionItem = new RelateCheckVo();
                        permissionItem.setId(id);
                        permissionItem.setRelativeId(permission.getPermissionId());
                        permissionItem.setName(permission.getName());
                        permissionItem.setType(PermissionType.Permission);
                        if (rolePermissionDbList.stream().anyMatch(item -> item.getRoleId().equals(id) && item.getPermissionId().equals(permission.getPermissionId()))) {
                            permissionItem.setIsCheck(true);
                        } else {
                            permissionItem.setIsCheck(false);
                        }
                        if (functionItem.getSubList().size() == 0 || true) permissionItem.setShowDetail(true);
                        functionItem.getSubList().add(permissionItem);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public void savePermission(List<RelateCheckVo> list) {
        if (CollectionUtils.isEmpty(list)) return;
        EnumSet<PermissionType> set = EnumSet.of(PermissionType.FirstMenu, PermissionType.SecondMenu, PermissionType.FunctionItem);
        RolePermission rp = new RolePermission();
        for (RelateCheckVo item : list) {
            rp.setRoleId(item.getId());
            rp.setPermissionId(item.getRelativeId());
            if (set.contains(item.getType()) && item.getSingleCheck()) {
                dao.add(rp);
            } else if (item.getType() == PermissionType.Permission && item.getIsCheck()) {
                dao.add(rp);
            } else {
                dao.deleteEntity(rp);
            }
        }
    }

    @Override
    public List<RolePermissionVo> queryRolePermission(String roleId) {
        return queryRolePermission(Arrays.asList(roleId));
    }

    @Override
    public List<RolePermissionVo> queryRolePermission(List<String> roleIdList) {
        if (CollectionUtils.isEmpty(roleIdList)) return Collections.EMPTY_LIST;
        return dao.queryByRoleList(roleIdList);
    }
}
