package com.zjy.baseframework.enums;

/**
 * Created by Administrator on 2018/2/27.
 */
public enum DbType {
    Oracle(0, "oracle"),
    Mysql(1, "mysql"),
    SqlServer(2, "oracle");

    private int value;
    private String name;

    DbType(int value, String name){
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }
    public String getName() {
        return name;
    }

    public static DbType getDbTypeByValue(int value) {
        for (DbType dbType : DbType.values()) {
            if(dbType.getValue() == value) return dbType;
        }
        throw new IllegalArgumentException("无效的数据库类别！value:" + value);
    }

    public static DbType getDbTypeByName(String name) {
        for (DbType dbType : DbType.values()) {
            if(dbType.getName().equals(name)) return dbType;
        }
        throw new IllegalArgumentException("无效的数据库类别！name:" + name);
    }
}
