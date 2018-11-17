package com.zjy.bll.vo;

import com.zjy.entities.RoleInfo;

public class RoleInfoVo extends RoleInfo {
    private String userId;
    private boolean isSave;

    public boolean getIsSave() {
        return isSave;
    }

    public void setIsSave(boolean isSave) {
        this.isSave = isSave;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
