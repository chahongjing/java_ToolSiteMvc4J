package com.zjy.baseframework.annotations;

import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SerializeEnum {
    /**
     * 序列化别名
     *
     * @return
     */
    String value() default StringUtils.EMPTY;
}
