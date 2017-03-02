package com.zjy.baseframework.eCharts.lineChart;

import com.zjy.baseframework.eCharts.common.ChartSeriesDataBase;

/**
 * 折线数据
 */
public class LineChartSeriesData extends ChartSeriesDataBase {
    /**
     * 构造函数
     * @param value 值
     */
    public LineChartSeriesData(String value) {
        this(value, null);
    }

    /**
     * 构造函数
     * @param value 值
     * @param name 名称
     */
    public LineChartSeriesData(String value, String name) {
        super.setName(name);
        super.setValue(value);
    }
}
