package com.zjy.baseframework.eCharts.lineChart;

import com.zjy.baseframework.eCharts.common.ChartSeriesDataBase;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 折线数据
/// </summary>
public class LineChartSeriesData extends ChartSeriesDataBase {
    /// <summary>
/// 构造函数
/// </summary>
/// <param name="value">值</param>
    public LineChartSeriesData(String value) {
        this(value, null);
    }

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="value">值</param>
/// <param name="name">名称</param>
    public LineChartSeriesData(String value, String name) {
        this.name = name;
        this.value = value;
    }
}
