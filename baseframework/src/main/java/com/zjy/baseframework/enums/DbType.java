package com.zjy.baseframework.enums;

import com.zjy.baseframework.mybatis.IBaseEnum;

/**
 * Created by Administrator on 2018/2/27.
 */
public enum DbType implements IBaseEnum {
    Oracle(0, "oracle", "oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@127.0.0.1:1521:orcl"),
    Mysql(1, "mysql", "com.mysql.jdbc.Driver", "jdbc:mysql://localhost/ToolSiteMvc4J"),
    SqlServer(2, "sqlserver", "com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://PC201404190064\\\\MSSQL; DatabaseName=ToolSiteMvc4J"),
    Sqlite(3, "sqlite", "org.sqlite.JDBC", "jdbc:sqlite::resource:db/app.db");

    private int value;
    private String code;
    private String driver;
    private String url;

    DbType(int value, String code, String driver, String url){
        this.value = value;
        this.code = code;
        this.driver = driver;
        this.url = url;
    }

    public int getValue() {
        return value;
    }
    public String getCode() {
        return code;
    }
    public String getDriver() {
        return driver;
    }
    public String getUrl() {
        return url;
    }

    public static DbType getByValue(int value) {
        return IBaseEnum.getByValue(DbType.class, value);
    }

    public static DbType getByCode(String name) {
        return IBaseEnum.getByCode(DbType.class, name);
    }
}
