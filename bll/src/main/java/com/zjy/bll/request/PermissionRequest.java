package com.zjy.bll.request;

import com.zjy.bll.common.PageInfomation;

public class PermissionRequest extends PageInfomation {
    private String functionId;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }
}
