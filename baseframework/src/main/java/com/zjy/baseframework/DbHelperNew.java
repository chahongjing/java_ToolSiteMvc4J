package com.zjy.baseframework;


import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by chahongjing on 2017/10/8.
 */
public class DbHelperNew {

    public static Connection getConnection() {
        String url = PropertiesHelper.getInstance().getProperties("db.url");
        String driver = PropertiesHelper.getInstance().getProperties("db.driverClassName");
        String user = PropertiesHelper.getInstance().getProperties("db.userName");
        String password = PropertiesHelper.getInstance().getProperties("db.password");
        DbUtils.loadDriver(driver);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static int insert(String sql) {
        return update(sql);
    }

    public static int delete(String sql) {
        return update(sql);
    }

    public static int update(String sql) {
        Connection conn = null;
        int r = -1;
        try {
            conn = getConnection();
            QueryRunner qr = new QueryRunner();
            // insert方法返回插入行的主键
            r = qr.update(conn, sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return r;
    }

    public static <T> T get(String sql, Class<T> clazz) {
        List list = getList(sql, clazz);
        if(list != null && !list.isEmpty()) {
            return (T)list.get(0);
        } else {
            return null;
        }
    }

    public static <T> List<T> getList(String sql, Class<T> clazz) {
        Connection conn = null;
        List<T> r = null;
        try {
            conn = getConnection();
            QueryRunner qr = new QueryRunner();
            r = (List<T>)qr.query(conn, sql, new BeanListHandler(clazz));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return r;
    }
}
