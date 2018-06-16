package com.zjy.baseframework.enums;

/**
 * Created by Administrator on 2018/2/27.
 */
public enum DbType {
    Oracle(0, "oracle", "oracle.jdbc.OracleDriver"),
    Mysql(1, "mysql", "com.mysql.jdbc.Driver"),
    SqlServer(2, "sqlserver", "com.microsoft.sqlserver.jdbc.SQLServerDriver"),
    Sqlite(3, "sqlite", "org.sqlite.JDBC");

    private int value;
    private String name;
    private String driver;

    DbType(int value, String name, String driver){
        this.value = value;
        this.name = name;
        this.driver = driver;
    }

    public int getValue() {
        return value;
    }
    public String getName() {
        return name;
    }
    public String getDriver() {
        return driver;
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
