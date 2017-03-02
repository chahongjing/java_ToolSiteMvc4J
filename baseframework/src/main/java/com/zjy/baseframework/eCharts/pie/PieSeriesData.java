package com.zjy.baseframework.eCharts.pie;

import com.zjy.baseframework.eCharts.common.ChartSeriesDataBase;

/**
 * 仪表盘数据
 */
public class PieSeriesData extends ChartSeriesDataBase {
    /**
     * 构造函数
     * @param value 值
     */
    public PieSeriesData(float value) {
        this(value, null);
    }

    /**
     * 构造函数
     * @param value 值
     * @param name 名称
     */
    public PieSeriesData(float value, String name) {
        super.setName(name);
        super.setValue(value);
    }
}