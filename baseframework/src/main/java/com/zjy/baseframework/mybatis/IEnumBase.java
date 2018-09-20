package com.zjy.baseframework.mybatis;


/**
 * Created by Administrator on 2018/5/15.
 */
public interface IEnumBase {
    default int getValue(){
        throw new UnsupportedOperationException("未实现getValue方法");
    }
    default String getCode(){
        throw new UnsupportedOperationException("未实现getCode方法");
    }

    static <E extends Enum<E> & IEnumBase> E getByValue(Class<E> enumClass, Integer value) {
        if (value == null) return null;
        E[] enumConstants = enumClass.getEnumConstants();
        for (E item : enumConstants) {
            if (value.equals(item.getValue())) {
                return item;
            }
        }
        return null;
    }
}