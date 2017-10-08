package com.zjy.baseframework;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by chahongjing on 2017/10/8.
 */
public class ReflectionHelper {

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
        return clazz;
    }
}
