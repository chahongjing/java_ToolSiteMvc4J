package com.zjy.baseframework.enums;

/**
 * Created by chahongjing on 2018/3/17.
 */
public enum DbType {
    Mysql("mysql"),
    Oracle("oracle"),
    SqlServer("sqlserver");

    private String value;

    DbType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DbType getDbTypeByName(String value) {
        for (DbType dbType : DbType.values()) {
            if(dbType.getValue().equalsIgnoreCase(value)) {
                return dbType;
            }
        }
        throw new RuntimeException(String.format("错误的数据库类型枚举值DbType：%s", value));
    }
}
