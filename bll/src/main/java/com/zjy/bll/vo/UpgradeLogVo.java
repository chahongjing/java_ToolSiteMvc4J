package com.zjy.bll.vo;

import com.zjy.entities.UpgradeLog;

import java.util.List;

public class UpgradeLogVo extends UpgradeLog {
    private List<UpgradeLogItem> contentList;

    public List<UpgradeLogItem> getContentList() {
        return contentList;
    }

    public void setContentList(List<UpgradeLogItem> contentList) {
        this.contentList = contentList;
    }
}
