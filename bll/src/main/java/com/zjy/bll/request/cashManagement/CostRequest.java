package com.zjy.bll.request.cashManagement;

import com.zjy.bll.common.PageInfomation;

/**
 * Created by chahongjing on 2017/6/10.
 */
public class CostRequest extends PageInfomation {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
