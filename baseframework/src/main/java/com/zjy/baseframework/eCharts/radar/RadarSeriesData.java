package com.zjy.baseframework.eCharts.radar;

import com.zjy.baseframework.eCharts.common.ChartSeriesDataBase;

import java.util.List;

/**
 * 仪表盘数据
 */
public class RadarSeriesData extends ChartSeriesDataBase {
    /**
     * 构造函数
     * @param value 值
     */
    public RadarSeriesData(List<Float> value) {
        this(value, null);
    }

    /**
     * 构造函数
     * @param value 值
     * @param name 名称
     */
    public RadarSeriesData(List<Float> value, String name) {
        super.setName(name);
        super.setValue(value);
    }
}