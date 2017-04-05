package com.zjy.baseframework;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DbHelper {
    private static ConcurrentHashMap<String, List<Field>> map = new ConcurrentHashMap();
    public static ResultSet testSelect() {
        String sql = PropertiesHelper.getInstance().getProperties("db.testSql");
        try (Connection conn = getConnection()) {
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

    public static List toList(Class clazz) {
        //String sql = PropertiesHelper.getInstance().getProperties("db.testSql");
        String sql = "select * from userinfo";
        try {
            Connection conn = getConnection();
            PreparedStatement pSta = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rSet = pSta.executeQuery();
            return populate(rSet, clazz);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int testInsert() {
        String sql = "insert into userinfo(UserGuid, UserCode, UserName, [Password], Sex, Birthday, IsSystem)\n" +
                "values(?, 'testuser', '测试数据', '1', 1, getdate(), 1)";
        try (Connection conn = getConnection()) {
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

    public static int testUpdate() {
        String sql = "update userinfo set birthday = ? where userguid = ?";
        try (Connection conn = getConnection()) {
            // 获取数据库连接
            PreparedStatement pSta = conn.prepareStatement(sql);
            pSta.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
            pSta.setString(2, "D8E6B877-3645-4063-A25C-495606B95349");
            //pSta.setString(2, UUID.randomUUID().toString());
            conn.setAutoCommit(false);
            int result = pSta.executeUpdate();
            conn.commit();
            pSta.close();
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

    public static int testDelete() {
        String sql = "delete from userinfo where userguid = ?";
        try (Connection conn = getConnection()) {
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

    /**
     * @param rs
     * @param clazz
     * @return
     */
    public static List populate(ResultSet rs, Class clazz) {
        List list = new ArrayList();
        boolean flag;
        try {
            // 结果集的元素对象
            ResultSetMetaData rsmd = rs.getMetaData();
            // 获取结果集的元素个数
            int colCount = rsmd.getColumnCount();
            // 业务对象的属性数组
            List<Field> fields = null;
            if(map.containsKey(clazz.getName())) {
                fields = map.get(clazz.getName());
            } else {
                fields = Arrays.asList(clazz.getDeclaredFields());
                map.put(clazz.getName(), fields);
            }
            // 对每一条记录进行操作
            while (rs.next()) {
                // 构造业务对象实体
                Object obj = clazz.newInstance();
                // 将每一个字段取出进行赋值
                for (int i = 0; i < colCount; i++) {
                    Object value = rs.getObject(i + 1);
                    // 寻找该列对应的对象属性
                    for (int j = 0; j < fields.size(); j++) {
                        Field f = fields.get(j);
                        // 如果匹配进行赋值
                        if (f.getName().equalsIgnoreCase(rsmd.getColumnLabel(i + 1))) {
                            if (f.getType().isPrimitive() && value == null) {
                                continue;
                            }
                            flag = f.isAccessible();
                            f.setAccessible(true);
                            f.set(obj, value);
                            f.setAccessible(flag);
                        }
                    }
                }
                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return list;
    }
}