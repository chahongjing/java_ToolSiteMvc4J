package com.zjy.baseframework.mybatis;

/**
 * Created by Administrator on 2018/5/15.
 */
public interface IBaseCodeEnum {
    int getValue();

    IBaseCodeEnum getByValue(int code);
}