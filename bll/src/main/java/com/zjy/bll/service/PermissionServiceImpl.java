package com.zjy.bll.service;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.ServiceException;
import com.zjy.bll.common.BaseService;
import com.zjy.bll.dao.PermissionDao;
import com.zjy.bll.request.PermissionRequest;
import com.zjy.bll.vo.PermissionVo;
import com.zjy.entities.Permission;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class PermissionServiceImpl extends BaseService<PermissionDao, Permission> implements PermissionService {
    @Override
    public PageInfo<PermissionVo> queryPageList(PermissionRequest request) {
        Permission permission = new Permission();
        permission.setName(request.getName());
        PageInfo<PermissionVo> pageInfo = (PageInfo<PermissionVo>)super.queryPageList(request, permission);
        return pageInfo;
    }

    public PermissionVo get(String permissionId) {
        return (PermissionVo)super.get(permissionId);
    }

    @Override
    public PermissionVo getVo(String permissionId) {
        PermissionVo vo = get(permissionId);
        if(vo == null) {
            vo = new PermissionVo();
            vo.setPermissionId(permissionId);
            vo.setIsSave(false);
        } else {
            vo.setIsSave(true);
        }
        return vo;
    }

    /**
     * 保存用户
     * @param permissionInfo
     */
    @Override
    @Transactional
        public void savePermission(PermissionVo permissionInfo) {
        PermissionVo vo = getVo(permissionInfo.getPermissionId());
        beforeCheck(permissionInfo);
        // 处理密码
        if(vo.getIsSave()) {
            update(permissionInfo);
        } else {
            add(permissionInfo);
        }
    }

    protected void beforeCheck(PermissionVo menu) {
        if(StringUtils.isBlank(menu.getName())) {
            throw new ServiceException("请输入功能名称！");
        }
        Map<String, BigDecimal> map = dao.queryRepeatCount(menu.getMenuId(), menu.getCode());
        if(map != null && map.containsKey("CODECOUNT") && map.get("CODECOUNT").intValue() > 0) {
            throw new ServiceException("功能名称重复！");
        }
    }
}
