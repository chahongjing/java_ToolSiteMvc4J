package com.zjy.baseframework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2018/1/2.
 */
public class LogHelper {

    private LogHelper() {}
    /**
     * 公用日志处理
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Object> Logger getLogger(Class<T> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
