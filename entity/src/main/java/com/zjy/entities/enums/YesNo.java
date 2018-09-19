package com.zjy.entities.enums;

import com.zjy.baseframework.mybatis.CodeEnumUtil;
import com.zjy.baseframework.mybatis.IBaseCodeEnum;

public enum YesNo implements IBaseCodeEnum {
    NO(0, "否"),         //开启
    YES(1, "是");

    private int value;

    private String name;

    YesNo(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public int getValue() { return this.value; }

    public String getName() {
        return name;
    }
    public YesNo getByValue(int value){
        return CodeEnumUtil.getByValue(YesNo.class, value);
    }
}
