package com.zjy.bll.enums;

import com.zjy.baseframework.mybatis.IBaseEnum;

public enum SwitchEnum implements IBaseEnum {
    SWITCH_ONE(1, "第一个开关"),
    SWITCH_TWO(2, "第二个开关");

    private final int value;
    private final String name;
    public static final String SWITCH_KEY = "SWITCH_KEY";
    public static final String SWITCH_OPEN = "1";
    public static final String SWITCH_CLOSE = "0";


    SwitchEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
