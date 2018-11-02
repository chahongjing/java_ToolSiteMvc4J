package com.zjy.bll.vo;

import com.zjy.entities.ConfigInfo;

/**
 * Created by Administrator on 2018/11/1.
 */
public class ConfigInfoVo extends ConfigInfo {
    private String typeName;
    private boolean isSave;

    public boolean getIsSave() {
        return this.isSave;
    }

    public void setIsSave(boolean isSave) {
        this.isSave = isSave;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
