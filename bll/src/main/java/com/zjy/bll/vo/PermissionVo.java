package com.zjy.bll.vo;

import com.zjy.entities.Permission;

public class PermissionVo extends Permission {
    private boolean isSave;
    private String menuName;

    public boolean getIsSave() {
        return isSave;
    }

    public void setIsSave(boolean isSave) {
        this.isSave = isSave;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
