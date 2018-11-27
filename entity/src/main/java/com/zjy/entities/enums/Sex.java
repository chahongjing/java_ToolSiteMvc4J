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
    Male(0, "男", 1),         //开启
    Female(1, "女", 2);

    private int value;

    private String name;

    private int order;

    Sex(int value, String name, int order) {
        this.value = value;
        this.name = name;
        this.order = order;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public static Sex getByValue(int value) {
        return IBaseEnum.getByValue(Sex.class, value);
    }
}
