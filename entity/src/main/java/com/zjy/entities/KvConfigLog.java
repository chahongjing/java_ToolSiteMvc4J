package com.zjy.entities;


import java.util.Date;

public class KvConfigLog {
    private String id;
    private String code;
    private String value;
    private String createBy;
    private String kvId;
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getKvId() {
        return kvId;
    }

    public void setKvId(String kvId) {
        this.kvId = kvId;
    }
}
