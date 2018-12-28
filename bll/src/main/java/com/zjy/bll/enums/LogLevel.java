package com.zjy.bll.enums;

import com.zjy.baseframework.annotations.MybatisFieldEnum;
import com.zjy.baseframework.mybatis.IBaseEnum;

/**
 * Created by Administrator on 2018/12/22.
 */
@MybatisFieldEnum
public enum LogLevel implements IBaseEnum {
    ERROR(1),
    WARN(2),
    INFO(3),
    DEBUG(4),
    OTHER(0);

    private int value;

    LogLevel(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    public static LogLevel getByName(String name) {
        for (LogLevel logLevel : LogLevel.values()) {
            if (logLevel.name().equalsIgnoreCase(name)) {
                return logLevel;
            }
        }
        return LogLevel.OTHER;
    }
}
