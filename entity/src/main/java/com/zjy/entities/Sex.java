package com.zjy.entities;

import com.zjy.baseframework.mybatis.BaseCodeEnum;

/**
 * Created by Administrator on 2018/5/15.
 */
public enum Sex implements BaseCodeEnum {
    Male(1),         //开启
    Female(2);

    private int code;
    Sex(int code) { this.code = code; }

    @Override
    public int getCode() { return this.code; }
}
