package com.zjy.baseframework.eCharts.enums;

/**
 * 触发类型
 */
public enum TriggerType
{
    /**
     * 坐标轴触发，主要在柱状图，折线图等会使用类目轴的图表中使用。
     */
    Axis,
    /**
     * 数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。
     */
    Item
}