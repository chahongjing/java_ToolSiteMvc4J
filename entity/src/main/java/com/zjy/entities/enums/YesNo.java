package com.zjy.entities.enums;

import com.zjy.baseframework.mybatis.IEnumBase;

public enum YesNo implements IEnumBase {
    NO(0, "否"),
    YES(1, "是");

    private int value;

    private String name;

    YesNo(int value, String name) {
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
}
