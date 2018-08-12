package com.zjy.baseframework.mybatis;

/**
 * Created by Administrator on 2018/5/15.
 */
public interface IBaseCodeEnum {
    int getCode();

    IBaseCodeEnum getByCode(int code);
}