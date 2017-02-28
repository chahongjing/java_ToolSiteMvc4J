package com.zjy.baseframework.eCharts.common;

/**
 * Created by Administrator on 2017/2/28.
 */
/// <summary>
/// 坐标轴指示器
/// </summary>
public class AxisPointer
{
    /// <summary>
    /// 类型(line,shadow)
    /// </summary>
    public String type;

    /// <summary>
    /// 构造函数
    /// </summary>
    public AxisPointer()
    { this(LineType.Line);}

    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="type">类型</param>
    public AxisPointer(LineType type)
    {
        this.type = type.ToString().ToLower();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
