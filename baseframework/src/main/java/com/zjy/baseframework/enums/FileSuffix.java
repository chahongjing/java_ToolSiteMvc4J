package com.zjy.baseframework.enums;

/**
 * Created by Administrator on 2018/1/19.
 */
public enum FileSuffix {
    XLS(".xls", "excel2003"),
    XLSX(".xlsx", "excel2007及以上");

    private String value;
    private String remark;

    FileSuffix(String value, String remark){
        this.value = value;
        this.remark = remark;
    }

    public String getValue() {
        return value;
    }
}
