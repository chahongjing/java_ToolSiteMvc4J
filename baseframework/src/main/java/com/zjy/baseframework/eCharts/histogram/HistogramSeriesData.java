package com.zjy.baseframework.eCharts.histogram;

import com.zjy.baseframework.eCharts.common.ChartSeriesDataBase;

/**
 * Created by chahongjing on 2017/2/28.
 */
public class HistogramSeriesData extends ChartSeriesDataBase {

    /**
     * 构造函数
     * @param value 值
     */
    public HistogramSeriesData(String value) {
        this(value, null);
    }

    /**
     * 构造函数
     * @param value 值
     * @param name 名称
     */
    public HistogramSeriesData(String value, String name) {
        super.setName(name);
        super.setValue(value);
    }
}
