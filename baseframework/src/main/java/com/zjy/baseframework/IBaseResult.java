package com.zjy.baseframework;

import com.zjy.baseframework.enums.ResultStatus;

public interface IBaseResult {
    /// <summary>
    /// 状态字符串
    /// </summary>
    ResultStatus status = ResultStatus.OK;

    /// <summary>
    /// 返回信息
    /// </summary>
    String message = "";
}
