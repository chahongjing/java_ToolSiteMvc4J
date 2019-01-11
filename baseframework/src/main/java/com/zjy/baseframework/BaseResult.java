package com.zjy.baseframework;

import com.alibaba.fastjson.JSON;
import com.zjy.baseframework.enums.ResultStatus;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class BaseResult<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ResultStatus status;
    private String message;
    private T value;

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    /// <summary>
    /// 无参构造函数
    /// </summary>
    public BaseResult() {
        this(ResultStatus.OK, null, null);
    }

    /// <summary>
    /// 有参构造函数
    /// </summary>
    /// <param name="status">返回状态</param>
    public BaseResult(ResultStatus status) {
        this(status, null, null);
    }


    /// <summary>
    /// 有参构造函数
    /// </summary>
    /// <param name="status">返回状态</param>
    /// <param name="message">返回信息</param>
    public BaseResult(ResultStatus status, String message) {
        this(status, message, null);
    }


    /// <summary>
    /// 有参构造函数
    /// </summary>
    /// <param name="status">返回状态</param>
    /// <param name="val">记录</param>
    public BaseResult(ResultStatus status, T val) {
        this(status, null, val);
    }


    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="result">结果</param>
    public BaseResult(IBaseResult result) {
        this(result.status, result.message, null);
    }


    /// <summary>
    /// 有参构造函数
    /// </summary>
    /// <param name="status">返回状态</param>
    /// <param name="message">返回信息</param>
    /// <param name="val">记录</param>
    public BaseResult(ResultStatus status, String message, T val) {
        this.status = status;
        this.message = message;
        this.value = val;
    }

    /// <summary>
    /// OK
    /// </summary>
    /// <returns></returns>
    public static <T> BaseResult<T> OK() {
        return OK(null);
    }

    /// <summary>
    /// OK
    /// </summary>
    /// <param name="value">返回值</param>
    /// <returns></returns>
    public static <T> BaseResult<T> OK(T value) {
        return OK(value, null);
    }

    /// <summary>
    /// OK
    /// </summary>
    /// <param name="message">提示信息</param>
    /// <param name="value">返回值</param>
    /// <returns></returns>
    public static <T> BaseResult<T> OK(T value, String message) {
        return new BaseResult<>(ResultStatus.OK, message, value);
    }

    /// <summary>
    /// NO
    /// </summary>
    /// <returns></returns>
    public static <T> BaseResult<T> NO() {
        return NO(StringUtils.EMPTY);
    }

    /// <summary>
    /// NO
    /// </summary>
    /// <param name="message">提示信息</param>
    /// <returns></returns>
    public static <T> BaseResult<T> NO(String message) {
        return NO(message, null);
    }

    /// <summary>
    /// NO
    /// </summary>
    /// <param name="message">提示信息</param>
    /// <param name="value">返回值</param>
    /// <returns></returns>
    public static <T> BaseResult<T> NO(String message, T value) {
        return new BaseResult<>(ResultStatus.NO, message, value);
    }

    /// <summary>
    /// ERROR
    /// </summary>
    /// <param name="message">提示信息</param>
    /// <returns></returns>
    public static <T> BaseResult<T> ERROR(String message) {
        return ERROR(message, null);
    }

    /// <summary>
    /// ERROR
    /// </summary>
    /// <param name="message">提示信息</param>
    /// <param name="value">返回值</param>
    /// <returns></returns>
    public static <T> BaseResult<T> ERROR(String message, T value) {
        return new BaseResult<>(ResultStatus.ERROR, message, value);
    }
}
