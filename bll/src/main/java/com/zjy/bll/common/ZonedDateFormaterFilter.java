package com.zjy.bll.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ValueFilter;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by jyzeng on 2018/10/24.
 */
public class ZonedDateFormaterFilter implements ValueFilter {
    protected static DateTimeFormatter dtf;

    static {
        dtf = DateTimeFormatter.ofPattern(((SimpleDateFormat) MyCustomDateEditor.getUtcSfd()).toPattern()).withZone(ZoneOffset.UTC);
    }
    @Override
    public Object process(Object object, String name, Object value) {
        if (value != null && value instanceof ZonedDateTime) {
            ZonedDateTime val = (ZonedDateTime) value;
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
                        value = val.format(dtf);
                    } else {
                        for (JSONField anno : annos) {
                            if (StringUtils.isBlank(anno.format())) continue;
                            value = val.format(DateTimeFormatter.ofPattern(anno.format()));
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
