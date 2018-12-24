package com.zjy.bll.request;

import com.zjy.bll.common.PageInfomation;
import com.zjy.entities.enums.Sex;

/**
 * @author chahongjing
 * @create 2016-12-27 21:10
 */
public class UserInfoRequest extends PageInfomation {
    private String userName;
    private Sex sex;
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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
