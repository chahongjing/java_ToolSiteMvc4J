package com.zjy.bll.vo;

import com.zjy.entities.RolePermission;

public class RolePermissionVo extends RolePermission {
    private String roleCode;
    private String permissionCode;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }
}
