package com.zjy.baseframework.enums;

import com.zjy.baseframework.annotations.SerializeEnum;
import com.zjy.baseframework.mybatis.IBaseEnum;

@SerializeEnum
public enum ResultStatus implements IBaseEnum {
    OK(1, "成功"),
    NO(2, "失败"),
    UNAUTHENTICATION(3, "未登录"),
    UNAUTHORIZED(4, "未授权"),
    ERROR(5, "错误");

    private int value;

    private String name;

    ResultStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    public String getName() {
        return name;
    }

    public static ResultStatus getByValue(int value) {
        return IBaseEnum.getByValue(ResultStatus.class, value);
    }
}
