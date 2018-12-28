package com.zjy.baseframework;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class DbHelper {
    private DbHelper() {}
    private static boolean isLoadDirve = false;
    private static ConcurrentHashMap<String, List<Field>> map = new ConcurrentHashMap();
    public static ResultSet testSelect() {
        String sql = PropertiesHelper.getInstance().getProperties("db.testSql");
        try (Connection conn = getConnection();
             PreparedStatement pSta = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY);) {
            // pSta.setString(1, "abc");
            ResultSet rSet = pSta.executeQuery();
            // rSet.last();rSet.getRow();
            return rSet;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List toList(Class clazz) {
        //String sql = PropertiesHelper.getInstance().getProperties("db.testSql");
        String sql = "select * from userinfo";
        return toList(clazz, sql);
    }


    public static List toList(Class clazz, String sql) {
        try (Connection conn = getConnection();
             PreparedStatement pSta = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY);){
            ResultSet rSet = pSta.executeQuery();
            return populate(rSet, clazz);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }

    public static int testInsert() {
        String sql = "insert into userinfo(UserId, UserCode, UserName, [Password], Sex, Birthday, IsSystem)\n" +
                "values(?, 'testuser', '测试数据', '1', 1, getdate(), 1)";
        try (Connection conn = getConnection();
            PreparedStatement pSta = conn.prepareStatement(sql);) {
            pSta.setString(1, "D8E6B877-3645-4063-A25C-495606B95349");

            return pSta.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int testUpdate() {
        String sql = "update userinfo set birthday = ? where userId = ?";
        try (Connection conn = getConnection();
             PreparedStatement pSta = conn.prepareStatement(sql);) {
            // 获取数据库连接

            pSta.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
            pSta.setString(2, "D8E6B877-3645-4063-A25C-495606B95349");
            //pSta.setString(2, UUID.randomUUID().toString());
            conn.setAutoCommit(false);
            int result = pSta.executeUpdate();
            conn.commit();
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int testDelete() {
        String sql = "delete from userinfo where userId = ?";
        try (Connection conn = getConnection();
             PreparedStatement pSta = conn.prepareStatement(sql);) {
            pSta.setString(1, "D8E6B877-3645-4063-A25C-495606B95349");
            //pSta.setString(1, UUID.randomUUID().toString());

            return pSta.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // 反射数据库驱动程序类
        if(!isLoadDirve) {
            Class.forName(PropertiesHelper.getInstance().getProperties("db.driverClassName"));
            isLoadDirve = true;
        }

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
                            if(value == null) {
                                continue;
                            }
                            if(f.getType().equals(Boolean.class) || f.getType().equals(boolean.class)) {
                                f.set(obj, Boolean.parseBoolean(String.valueOf(value)));
                            } else if(f.getType().equals(Byte.class) || f.getType().equals(byte.class)) {
                                f.set(obj, Byte.parseByte(String.valueOf(value)));
                            } else if(f.getType().equals(Character.class) || f.getType().equals(char.class)) {
                                f.set(obj, value);
                            } else if(f.getType().equals(Double.class) || f.getType().equals(double.class)) {
                                f.set(obj, Double.parseDouble(String.valueOf(value)));
                            } else if(f.getType().equals(Float.class) || f.getType().equals(float.class)) {
                                f.set(obj, Float.parseFloat(String.valueOf(value)));
                            } else if(f.getType().equals(Integer.class) || f.getType().equals(int.class)) {
                                f.set(obj, Integer.parseInt(String.valueOf(value)));
                            } else if(f.getType().equals(Long.class) || f.getType().equals(long.class)) {
                                f.set(obj, Long.parseLong(String.valueOf(value)));
                            } else if(f.getType().equals(Short.class) || f.getType().equals(short.class)) {
                                f.set(obj, Short.parseShort(String.valueOf(value)));
                            } else if(f.getType().equals(String.class)) {
                                f.set(obj, String.valueOf(value));
                            } else {
                                f.set(obj, value);
                            }
                            f.setAccessible(flag);
                        }
                    }
                }
                list.add(obj);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return list;
    }
}