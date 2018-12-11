package com.zjy.bll.service;

import com.zjy.bll.common.BaseService;
import com.zjy.bll.dao.UserRoleDao;
import com.zjy.bll.vo.RelateCheckVo;
import com.zjy.bll.vo.RoleInfoVo;
import com.zjy.bll.vo.UserRoleVo;
import com.zjy.entities.UserRole;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleServiceImpl extends BaseService<UserRoleDao, UserRole> implements UserRoleService {

    @Autowired
    protected RoleInfoService roleInfoSrv;

    @Autowired
    protected MenuService menuSrv;

    @Autowired
    protected FunctionInfoService functionInfoSrv;

    @Autowired
    protected PermissionService permissionSrv;

    @Override
    public List<RelateCheckVo> queryAllRoleWithUserRole(String userId) {
        List<RelateCheckVo> list = new ArrayList<>();
        RelateCheckVo root, role;
        root = new RelateCheckVo();
        root.setName("角色列表");
        if (list.size() == 0) root.setShowDetail(true);
        list.add(root);
        List<RoleInfoVo> roleInfoVos = roleInfoSrv.queryAllRole();
        List<UserRoleVo> userRoleList = this.queryListByUserId(userId);
        for (RoleInfoVo roleInfoVo : roleInfoVos) {
            role = new RelateCheckVo();
            role.setId(userId);
            role.setRelativeId(roleInfoVo.getRoleId());
            role.setName(roleInfoVo.getName());
            if (userRoleList.stream().anyMatch(item -> item.getRoleId().equals(roleInfoVo.getRoleId()))) {
                role.setIsCheck(true);
            }
            root.getSubList().add(role);
        }
        return list;
    }

    @Override
    public void saveUserRole(List<RelateCheckVo> list) {
        if (CollectionUtils.isEmpty(list)) return;
        UserRole ur = new UserRole();
        for (RelateCheckVo item : list) {
            ur.setUserId(item.getId());
            ur.setRoleId(item.getRelativeId());
            if (item.getIsCheck()) {
                dao.add(ur);
            } else {
                dao.deleteEntity(ur);
            }
        }
    }

    @Override
    public List<UserRoleVo> queryListByUserId(String userId) {
        UserRoleVo urv = new UserRoleVo();
        urv.setUserId(userId);
        return (List<UserRoleVo>) dao.query(urv);
    }
}
