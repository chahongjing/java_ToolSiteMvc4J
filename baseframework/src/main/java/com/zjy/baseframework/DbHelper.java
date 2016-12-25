package com.zjy.baseframework;

import java.sql.*;

public class DbHelper {
    public static void Test() throws SQLException, ClassNotFoundException {
        // 反射数据库驱动程序类
        Class.forName(com.zjy.baseframework.PropertiesHelper.getInstance().getProperties("db.driverClassName"));

        // 获取数据库连接
        Connection conn = DriverManager.getConnection(
                com.zjy.baseframework.PropertiesHelper.getInstance().getProperties("db.url"),
                com.zjy.baseframework.PropertiesHelper.getInstance().getProperties("db.userName"),
                com.zjy.baseframework.PropertiesHelper.getInstance().getProperties("db.password"));
        // 定制sql命令
        System.out.println("1");
        // 创建该连接下的PreparedStatement对象
        PreparedStatement stmt = conn
                .prepareStatement(com.zjy.baseframework.PropertiesHelper.getInstance().getProperties("db.testSql"),
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // stmt.setString(1, "abc");

        ResultSet rSet = stmt.executeQuery();
        rSet.last();
        System.out.println("3:row" + rSet.getRow());
    }
}