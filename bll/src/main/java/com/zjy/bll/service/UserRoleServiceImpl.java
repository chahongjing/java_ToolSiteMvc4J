package com.zjy.bll.service;

import com.zjy.baseframework.KeyHelper;
import com.zjy.baseframework.interfaces.ICache;
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
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl extends BaseService<UserRoleDao, UserRole> implements UserRoleService {

    @Autowired
    protected RoleInfoService roleInfoSrv;

    @Autowired
    private ICache cacheHelper;

    @Override
    public List<RelateCheckVo> queryAllRoleWithUserRole(String userId) {
        List<RelateCheckVo> list = new ArrayList<>();
        RelateCheckVo root;
        RelateCheckVo role;
        root = new RelateCheckVo();
        root.setName("角色列表");
        if (CollectionUtils.isEmpty(list)) root.setShowDetail(true);
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
        String key = KeyHelper.getTsmKey(KeyHelper.USER_ROLE_LIST_KEY, userId);
        List<UserRoleVo> roleList = cacheHelper.get(key, List.class);
        if(CollectionUtils.isNotEmpty(roleList)) {
            return roleList;
        }

        UserRoleVo urv = new UserRoleVo();
        urv.setUserId(userId);
        roleList = (List<UserRoleVo>) dao.query(urv);
        cacheHelper.set(key, roleList);
        return roleList;
    }

    @Override
    public List<String> queryUserRoleCodeList(String userId) {
        List<UserRoleVo> userRoleList = queryListByUserId(userId);
        return userRoleList.stream().map(UserRoleVo::getRoleCode).distinct().collect(Collectors.toList());
    }
}
