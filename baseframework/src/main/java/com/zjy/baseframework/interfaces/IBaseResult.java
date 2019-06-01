package com.zjy.baseframework.interfaces;

import com.zjy.baseframework.enums.ResultStatus;
import org.apache.commons.lang3.StringUtils;

public interface IBaseResult {
    /// <summary>
    /// 状态字符串
    /// </summary>
    ResultStatus status = ResultStatus.OK;

    /// <summary>
    /// 返回信息
    /// </summary>
    String message = StringUtils.EMPTY;
}
