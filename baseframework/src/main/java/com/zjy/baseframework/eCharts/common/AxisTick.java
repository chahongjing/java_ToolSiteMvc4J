package com.zjy.baseframework.eCharts.common;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 轴刻度线
/// </summary>
public class AxisTick
{
    /// <summary>
    /// 刻度线和标签对齐
    /// </summary>
    public Boolean alignWithLabel;

    /// <summary>
    /// 构造函数
    /// </summary>
    public AxisTick()
    { this(false);}

    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="isAlignWithLabel">是否对齐</param>
    public AxisTick(Boolean isAlignWithLabel)
    {
        alignWithLabel = isAlignWithLabel;
    }

    public Boolean getAlignWithLabel() {
        return alignWithLabel;
    }

    public void setAlignWithLabel(Boolean alignWithLabel) {
        this.alignWithLabel = alignWithLabel;
    }
}
