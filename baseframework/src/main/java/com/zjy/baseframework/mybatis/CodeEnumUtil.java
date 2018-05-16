package com.zjy.baseframework.mybatis;

import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/15.
 */
public class CodeEnumUtil {

    public static <E extends Enum<?> & BaseCodeEnum> E codeOf(Class<E> enumClass, int code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getCode() == code)
                return e;
        }
        return null;
    }

    public static void registerTypeHandle(TypeHandlerRegistry typeHandlerRegistry) {
        // 扫描所有实体类
        List<String> classNames = null;
        try {
            classNames = list("com/zjy/entities");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String className : classNames) {
            // 处理路径成为类名
            className = className.replace('/', '.').replaceAll("\\.class", "");
            // 取得Class
            Class<?> aClass = null;
            try {
                aClass = Class.forName(className, false, CodeEnumUtil.class.getClassLoader());
                // 判断是否实现了BaseCodeEnum接口
                if (aClass.isEnum() && BaseCodeEnum.class.isAssignableFrom(aClass)) {
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
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver(CodeEnumUtil.class.getClassLoader());
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