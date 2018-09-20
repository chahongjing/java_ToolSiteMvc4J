package com.zjy.baseframework.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/15.
 */
public class CodeEnumTypeHandler<E extends Enum<E> & IEnumBase> extends BaseTypeHandler<IEnumBase> {

    private Class<E> type;

    public CodeEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, IEnumBase parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
//        if (rs.wasNull()) {
//            return null;
//        }
        int i = rs.getInt(columnName);
        return getTypeValue(i);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
//        if (rs.wasNull()) {
//            return null;
//        }
        int i = rs.getInt(columnIndex);
        return getTypeValue(i);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (cs.wasNull()) {
            return null;
        }
        int i = cs.getInt(columnIndex);
        return getTypeValue(i);
    }

    private E getTypeValue(int val) {
        try {
            return IEnumBase.getByValue(type, new Integer(val));
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot convert " + val + " to " + type.getSimpleName() + " by ordinal value.", ex);
        }
    }

    public static void registerTypeHandle(TypeHandlerRegistry typeHandlerRegistry, List<String> packages) {
        // 扫描所有实体类
        List<String> classNames = new ArrayList<>();
        try {
            // 枚举所在的包
            for (String pack : packages) {
                classNames.addAll(list(pack));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String className : classNames) {
            // 处理路径成为类名
            className = className.replace('/', '.').replaceAll("\\.class", "");
            if(className.indexOf("BaseTestCase") > -1) continue;
            // 取得Class
            Class<?> aClass = null;
            try {
                aClass = Class.forName(className, false, CodeEnumTypeHandler.class.getClassLoader());
                // 判断是否实现了IBaseCodeEnum接口
                if (aClass.isEnum() && IEnumBase.class.isAssignableFrom(aClass)) {
                    // 注册
                    typeHandlerRegistry.register(className, CodeEnumTypeHandler.class.getTypeName());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 返回路径下所有class
     *
     * @param packagePath        根路径
     * @return
     * @throws IOException
     */
    public static List<String> list(String packagePath) throws IOException {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver(CodeEnumTypeHandler.class.getClassLoader());
        Resource[] resources = resourceResolver.getResources("classpath*:" + packagePath + "/**/*.class");
        List<String> resourcePaths = new ArrayList<>();
        for (Resource resource : resources) {
            resourcePaths.add(preserveSubpackageName(resource.getURI().toString()));
        }
        return resourcePaths;
    }

    public static String preserveSubpackageName(String a) {
        String substring = a.substring(a.indexOf("!") + 2);
        return substring;
    }
}