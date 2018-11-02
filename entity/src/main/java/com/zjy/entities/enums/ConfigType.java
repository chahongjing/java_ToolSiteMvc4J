package com.zjy.entities.enums;

import com.zjy.baseframework.annotations.MybatisFieldEnum;
import com.zjy.baseframework.annotations.SerializeEnum;
import com.zjy.baseframework.mybatis.IBaseEnum;

/**
 * Created by Administrator on 2018/11/1.
 */
@SerializeEnum
@MybatisFieldEnum
public enum ConfigType implements IBaseEnum {
    OTHER(1, "其它");

    private int value;
    private String name;
    ConfigType(int value, String name){
        this.value = value;
        this.name = name;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getName() {
        return name;
    }

    public static ConfigType getByValue(int value) {
        return IBaseEnum.getByValue(ConfigType.class, value);
    }
}
