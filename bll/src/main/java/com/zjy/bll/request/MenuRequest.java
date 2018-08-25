package com.zjy.bll.request;

import com.zjy.bll.common.PageInfomation;

public class MenuRequest extends PageInfomation {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
