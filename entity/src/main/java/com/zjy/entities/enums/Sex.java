package com.zjy.entities.enums;

import com.zjy.baseframework.annotations.MybatisFieldEnum;
import com.zjy.baseframework.annotations.SerializeEnum;
import com.zjy.baseframework.mybatis.IBaseEnum;

/**
 * Created by Administrator on 2018/5/15.
 */
@SerializeEnum
@MybatisFieldEnum
public enum Sex implements IBaseEnum {
    Male(0, "男"),         //开启
    Female(1, "女");

    private int value;

    private String name;

    Sex(int value, String name) {
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

    public static Sex getByValue(int value) {
        return IBaseEnum.getByValue(Sex.class, value);
    }
}
