package com.zjy.baseframework.eCharts.common;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 系列列表数据
/// </summary>
/// <typeparam name="T">类型</typeparam>
public class ChartSeriesDataBase
{
    /// <summary>
    /// 名称
    /// </summary>
    public String name;
    /// <summary>
    /// 值
    /// </summary>
    public Object value;

    /// <summary>
    /// 指针,标识颜色
    /// </summary>
    public ItemStyle itemStyle;

    /// <summary>
    /// 构造函数
    /// </summary>
    public ChartSeriesDataBase()
    { }

    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="name">名称</param>
    public ChartSeriesDataBase(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public ItemStyle getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
    }
}