package com.zjy.bll.request;

import com.zjy.bll.common.PageInfomation;

/**
 * @author chahongjing
 * @create 2016-12-27 21:10
 */
public class UserInfoRequest extends PageInfomation {
    private String userName;
    private String orderBy;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getOrderBy() {
        return orderBy;
    }

    @Override
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
