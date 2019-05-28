package com.zjy.bll.request;

import com.zjy.bll.common.PageInfomation;
import com.zjy.entities.enums.LogLevel;

public class OperLogRequest extends PageInfomation {
    private LogLevel logLevel;

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }
}
