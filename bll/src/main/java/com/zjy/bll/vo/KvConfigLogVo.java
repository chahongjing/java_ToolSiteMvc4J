package com.zjy.bll.vo;

import com.zjy.entities.KvConfigLog;

public class KvConfigLogVo extends KvConfigLog {
    private String createByName;

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }
}
