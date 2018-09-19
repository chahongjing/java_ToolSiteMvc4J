package com.zjy.entities.enums;

import com.zjy.baseframework.mybatis.CodeEnumUtil;
import com.zjy.baseframework.mybatis.IBaseCodeEnum;

/**
 * Created by Administrator on 2018/5/15.
 */
public enum Sex implements IBaseCodeEnum {
    Male(0, "男"),         //开启
    Female(1, "女");

    private int value;

    private String name;

    Sex(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public int getValue() { return this.value; }

    public String getName() {
        return name;
    }
    public Sex getByValue(int value){
        return CodeEnumUtil.getByValue(Sex.class, value);
    }
}
