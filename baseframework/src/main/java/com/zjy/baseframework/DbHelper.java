package com.zjy.baseframework;

import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class DbHelper {
    protected static Logger logger = LogHelper.getLogger(DbHelper.class);
    private DbHelper() {}
    private static boolean isLoadDirve = false;
    private static ConcurrentHashMap<String, List<Field>> map = new ConcurrentHashMap();

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
            logger.error("获取列表失败！", e);
            return new ArrayList();
        }
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // 反射数据库驱动程序类
        if(!isLoadDirve) {
            Class.forName(PropertiesHelper.getInstance().getProperties("db.driverClassName"));
            isLoadDirve = true;
        }

        // 获取数据库连接
        return DriverManager.getConnection(
                PropertiesHelper.getInstance().getProperties("db.url"),
                PropertiesHelper.getInstance().getProperties("db.userName"),
                PropertiesHelper.getInstance().getProperties("db.password"));
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
            logger.error("Result转List失败！", e);
        }

        return list;
    }

    private void testJdbc() {
        try {
            Class.forName("driver");
            Connection conn = DriverManager.getConnection("", "", "");
            conn.setAutoCommit(false);
            PreparedStatement statement = conn.prepareStatement("sql");
            statement = conn.prepareCall("sql");
            statement.setObject(1, "param1");
            statement.setInt(1, 2);
            int i = statement.executeUpdate();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                rs.getString("userName");
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}