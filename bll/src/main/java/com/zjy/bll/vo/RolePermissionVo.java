package com.zjy.bll.vo;

import com.zjy.entities.RolePermission;

public class RolePermissionVo extends RolePermission {
    private String permissionCode;

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }
}
