package com.zjy.bll.request;

import com.zjy.bll.common.PageInfomation;

public class KvConfigLogRequest extends PageInfomation {
    private String kvId;

    public String getKvId() {
        return kvId;
    }

    public void setKvId(String kvId) {
        this.kvId = kvId;
    }
}
