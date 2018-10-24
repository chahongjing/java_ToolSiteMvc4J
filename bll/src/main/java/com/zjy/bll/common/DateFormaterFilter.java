package com.zjy.bll.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ValueFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jyzeng on 2018/10/24.
 */
public class DateFormaterFilter implements ValueFilter {
    @Override
    public Object process(Object object, String name, Object value) {
        if (value != null && value instanceof Date) {
            Date val = (Date) value;
            try {
                Field field = null;
                try {
                    field = object.getClass().getDeclaredField(name);
                } catch (NoSuchFieldException e) {
                    if (field == null) {
                        field = object.getClass().getSuperclass().getDeclaredField(name);
                    }
                }
                if (null != field) {
                    JSONField[] annos = field.getAnnotationsByType(JSONField.class);
                    if (annos == null || annos.length == 0) {
                        value = DateFormatUtils.formatUTC(val, ((SimpleDateFormat) MyCustomDateEditor.getUtcSfd()).toPattern());
                    } else {
                        for (JSONField anno : annos) {
                            if (StringUtils.isBlank(anno.format())) continue;
                            value = DateFormatUtils.format(val, anno.format());
                        }
                    }
                }
            } catch (NoSuchFieldException e) {
            }
            return value;
        } else {
            return value;
        }
    }
}
