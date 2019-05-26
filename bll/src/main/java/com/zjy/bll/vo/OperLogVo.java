package com.zjy.bll.vo;

import com.zjy.entities.OperLog;

import java.util.Date;

public class OperLogVo extends OperLog {
    private Date beginDate;

    private Date endDate;

    private String userName;

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
