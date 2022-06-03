package com.zjy.bll.request;

import com.zjy.bll.common.PageInfomation;

public class UpgradeLogRequest extends PageInfomation {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
