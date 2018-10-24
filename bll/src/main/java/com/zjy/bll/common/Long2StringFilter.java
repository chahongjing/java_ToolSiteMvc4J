package com.zjy.bll.common;

import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * Created by jyzeng on 2018/10/24.
 */
public class Long2StringFilter implements ValueFilter {
    @Override
    public Object process(Object object, String name, Object value) {
        if (value != null && value instanceof Long) {
            return String.valueOf(value);
        } else {
            return value;
        }
    }
}
