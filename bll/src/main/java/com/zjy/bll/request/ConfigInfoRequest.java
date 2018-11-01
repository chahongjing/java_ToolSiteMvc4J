package com.zjy.bll.request;

import com.zjy.bll.common.PageInfomation;

/**
 * Created by Administrator on 2018/11/1.
 */
public class ConfigInfoRequest extends PageInfomation {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
