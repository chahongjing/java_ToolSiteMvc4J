package com.zjy.baseframework;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chahongjing on 2017/10/8.
 */
public class ReflectionHelper {
    static List<Class> allClassList = new ArrayList<>();

    public static Class getClass(Class clazz) {
        Type superClass = clazz.getGenericSuperclass();
        if(superClass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType)superClass;
            Type[] typeArgs = parameterizedType.getActualTypeArguments();
            if(typeArgs != null && typeArgs.length > 0) {
                if(typeArgs[0] instanceof Class) {
                    clazz = (Class)typeArgs[0];
                }
            }
        }

        // 获取方法，后面的参数为获取的方法的签名参数类型
        // Method method = clazz.getDeclaredMethod("方法名称", HttpServletRequest.class, HttpServletResponse.class);
        return clazz;
    }

    public static List<Class> getProjectClassList() {
        if(CollectionUtils.isEmpty(allClassList)) {
            List<String> packages = new ArrayList<>();
            packages.add("com/zjy/baseframework");
            packages.add("com/zjy/entities");
            packages.add("com/zjy/bll");
            packages.add("com/zjy/web");
            // 枚举所在的包
            for (String pack : packages) {
                allClassList.addAll(ReflectionHelper.getClassFromPackage(pack));
            }
        }
        return allClassList;
    }

    /**
     * 返回路径下所有class
     *
     * @param packagePath        根路径
     * @return
     * @throws IOException
     */
    public static List<Class> getClassFromPackage(String packagePath) {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver(ReflectionHelper.class.getClassLoader());
        Resource[] resources = new Resource[0];
        try {
            resources = resourceResolver.getResources("classpath*:" + packagePath + "/**/*.class");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Class> classList = new ArrayList<>();
        String className;
        for (Resource resource : resources) {
            try {
                className = preserveSubpackageName(resource.getURI().toString());
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            className = className.replace('/', '.').replaceAll("\\.class", "");
            if(className.indexOf("BaseTestCase") > -1) continue;
            // 取得Class
            try {
                Class<?> aClass = Class.forName(className, false, ReflectionHelper.class.getClassLoader());
                classList.add(aClass);
            } catch (ClassNotFoundException e) {
                //e.printStackTrace();
            }
        }
        return classList;
    }

    public static String preserveSubpackageName(String a) {
        String substring = a.substring(a.indexOf("!") + 2);
        return substring;
    }
}
