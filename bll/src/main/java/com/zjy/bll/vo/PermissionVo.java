package com.zjy.bll.vo;

import com.zjy.entities.Permission;

public class PermissionVo extends Permission {
    private boolean isSave;
    private String pName;

    public boolean getIsSave() {
        return isSave;
    }

    public void setIsSave(boolean isSave) {
        this.isSave = isSave;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }
}
