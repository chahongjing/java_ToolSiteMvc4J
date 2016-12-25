package com.zjy.baseframework;

import com.sun.org.glassfish.gmbal.Description;

import java.lang.reflect.Field;

public class EnumHelper {
    @SuppressWarnings("restriction")
    public static String getDescription(Enum<?> enu) throws NoSuchFieldException, SecurityException {
        Class<? extends Enum> sc = enu.getClass();

        Field field = sc.getField(enu.toString());
        // field.getAnnotations();
        // 是否有此注解
        // field.isAnnotationPresent(Description.class);
        Description description = field.getAnnotation(Description.class);
        return description.value();
    }
}
