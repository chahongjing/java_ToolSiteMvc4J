package com.zjy.baseframework.eCharts.common;

import java.util.List;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 图标系列
/// </summary>
public class ChartSeriesBase
{
    /// <summary>
    /// 名称
    /// </summary>
    public String name;
    /// <summary>
    /// 数据
    /// </summary>
    public List<ChartSeriesDataBase> data;
    /// <summary>
    /// 类型(line, Gauge)
    /// </summary>
    public String type;

    /// <summary>
    /// 构造函数
    /// </summary>
    public ChartSeriesBase()
    { this(null);}

    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="name">名称</param>
    public ChartSeriesBase(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChartSeriesDataBase> getData() {
        return data;
    }

    public void setData(List<ChartSeriesDataBase> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
