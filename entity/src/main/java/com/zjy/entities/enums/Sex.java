package com.zjy.entities.enums;

import com.zjy.baseframework.mybatis.CodeEnumUtil;
import com.zjy.baseframework.mybatis.IBaseCodeEnum;

/**
 * Created by Administrator on 2018/5/15.
 */
public enum Sex implements IBaseCodeEnum {
    Male(0, "男"),         //开启
    Female(1, "女");

    private int code;

    private String name;

    Sex(int code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public int getCode() { return this.code; }

    public String getName() {
        return name;
    }
    public Sex getByCode(int code){
        return CodeEnumUtil.codeOf(Sex.class, code);
    }
}
