package com.zjy.baseframework.eCharts.common;

import com.zjy.baseframework.eCharts.enums.LineType;

/**
 * Created by Administrator on 2017/2/28.
 */
/// <summary>
/// 坐标轴指示器
/// </summary>
public class AxisPointer {
    /// <summary>
    /// 类型(line,shadow)
    /// </summary>
    private String type;

    /// <summary>
    /// 构造函数
    /// </summary>
    public AxisPointer() {
        this(LineType.Line);
    }

    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="type">类型</param>
    public AxisPointer(LineType type) {
        this.type = type.toString().toLowerCase();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
