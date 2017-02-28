package com.zjy.baseframework.eCharts.enums;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 触发类型
/// </summary>
public enum TriggerType
{
    /// <summary>
    /// 坐标轴触发，主要在柱状图，折线图等会使用类目轴的图表中使用。
    /// </summary>
    Axis,
    /// <summary>
    /// 数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。
    /// </summary>
    Item
}