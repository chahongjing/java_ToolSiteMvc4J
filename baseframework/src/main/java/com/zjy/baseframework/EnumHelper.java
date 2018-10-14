package com.zjy.baseframework;


import com.zjy.baseframework.annotations.SerializeEnum;
import com.zjy.baseframework.beans.EnumBean;
import com.zjy.baseframework.mybatis.IBaseEnum;
import org.springframework.context.annotation.Description;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EnumHelper {
    static List<Class<IBaseEnum>> serializeEnumList = new ArrayList<>();
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

    public static void initAllSerializeEnum(List<Class> classList) {
        for (Class aClass : classList) {
            if (aClass.isEnum() && IBaseEnum.class.isAssignableFrom(aClass) && aClass.isAnnotationPresent(SerializeEnum.class)) {
                serializeEnumList.add(aClass);
            }
        }
    }

    public static Map<Class<IBaseEnum>, Map<String, EnumBean>> getEnumBeanList() {
        Map<Class<IBaseEnum>, Map<String, EnumBean>> result = new LinkedHashMap<>();
        for (Class<IBaseEnum> iBaseEnumClass : serializeEnumList) {
            Map<String, EnumBean> map = new LinkedHashMap<>();
            for (IBaseEnum item : iBaseEnumClass.getEnumConstants()) {
                EnumBean bean = new EnumBean();
                bean.setKey(item.toString());
                bean.setValue(item.getValue());
                bean.setCode(item.getCode());
                bean.setName(item.getName());
                map.put(item.toString(), bean);
            }
            result.put(iBaseEnumClass, map);
        }
        return result;
    }
}
