package com.zjy.bll.vo;

import com.zjy.entities.UserInfo;

public class UserInfoVo extends UserInfo {
    private String mingcheng;
    private String sexName;
    private boolean isSave;

    public String getMingcheng() {
        return mingcheng;
    }

    public void setMingcheng(String mingcheng) {
        this.mingcheng = mingcheng;
    }

    public boolean getIsSave() {
        return isSave;
    }

    public void setIsSave(boolean isSave) {
        this.isSave = isSave;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }
}
