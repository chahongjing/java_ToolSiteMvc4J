package com.zjy.baseframework;

import java.sql.*;
import java.util.UUID;

public class DbHelper {
    public static ResultSet testSelect()  {
        String sql = PropertiesHelper.getInstance().getProperties("db.testSql");
        try (Connection conn = getConnection()){
            PreparedStatement pSta = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            // pSta.setString(1, "abc");
            ResultSet rSet = pSta.executeQuery();
            // rSet.last();rSet.getRow();
            return rSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static int testInsert()  {
        String sql = "insert into userinfo(UserGuid, UserCode, UserName, [Password], Sex, Birthday, IsSystem)\n" +
                "values(?, 'testuser', '测试数据', '1', 1, getdate(), 1)";
        try (Connection conn = getConnection()){
            PreparedStatement pSta = conn.prepareStatement(sql);
            pSta.setString(1, "D8E6B877-3645-4063-A25C-495606B95349");

            return pSta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public static int testUpdate()  {
        String sql = "update userinfo set birthday = ? where userguid = ?";
        try (Connection conn = getConnection()){
            // 获取数据库连接
            PreparedStatement pSta = conn.prepareStatement(sql);
            pSta.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
            pSta.setString(2, "D8E6B877-3645-4063-A25C-495606B95349");
            //pSta.setString(2, UUID.randomUUID().toString());
            conn.setAutoCommit(false);
            int result =  pSta.executeUpdate();
            conn.commit();
            conn.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public static int testDelete()  {
        String sql = "delete from userinfo where userguid = ?";
        try (Connection conn = getConnection()){
            PreparedStatement pSta = conn.prepareStatement(sql);
            pSta.setString(1, "D8E6B877-3645-4063-A25C-495606B95349");
            //pSta.setString(1, UUID.randomUUID().toString());

            return pSta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
            // 反射数据库驱动程序类
            Class.forName(com.zjy.baseframework.PropertiesHelper.getInstance().getProperties("db.driverClassName"));

            // 获取数据库连接
            Connection con = DriverManager.getConnection(
                    PropertiesHelper.getInstance().getProperties("db.url"),
                    PropertiesHelper.getInstance().getProperties("db.userName"),
                    PropertiesHelper.getInstance().getProperties("db.password"));
            return con;
    }
}