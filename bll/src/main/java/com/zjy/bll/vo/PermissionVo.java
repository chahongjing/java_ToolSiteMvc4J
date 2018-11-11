package com.zjy.bll.vo;

import com.zjy.entities.Permission;

public class PermissionVo extends Permission {
    private boolean isSave;
    private String functionName;

    public boolean getIsSave() {
        return isSave;
    }

    public void setIsSave(boolean isSave) {
        this.isSave = isSave;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
}
