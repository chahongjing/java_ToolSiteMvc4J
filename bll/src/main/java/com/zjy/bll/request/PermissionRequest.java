package com.zjy.bll.request;

import com.zjy.bll.common.PageInfomation;

public class PermissionRequest extends PageInfomation {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
