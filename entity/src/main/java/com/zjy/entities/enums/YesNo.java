package com.zjy.entities.enums;

import com.zjy.baseframework.mybatis.CodeEnumUtil;
import com.zjy.baseframework.mybatis.IBaseCodeEnum;

public enum YesNo implements IBaseCodeEnum {
    NO(0, "否"),         //开启
    YES(1, "是");

    private int code;

    private String name;

    YesNo(int code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public int getCode() { return this.code; }

    public String getName() {
        return name;
    }
    public YesNo getByCode(int code){
        return CodeEnumUtil.codeOf(YesNo.class, code);
    }
}
