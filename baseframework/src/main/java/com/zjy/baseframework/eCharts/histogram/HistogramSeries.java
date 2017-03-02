package com.zjy.baseframework.eCharts.histogram;

import com.zjy.baseframework.eCharts.common.ChartSeriesBase;
import com.zjy.baseframework.eCharts.common.ChartSeriesDataBase;
import com.zjy.baseframework.eCharts.enums.ChartType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 柱状图数据
 */
public class HistogramSeries extends ChartSeriesBase {
    /**
     * 宽度
     */
    private String barWidth;

    /**
     * 构造函数
     */
    public HistogramSeries() {
        this(null);
    }

    /**
     * 构造函数
     * @param name 名称
     */
    public HistogramSeries(String name) {
        this(name, null);
    }

    /**
     * 构造函数
     * @param name 名称
     * @param data 值
     */
    public HistogramSeries(String name, List<HistogramSeriesData> data) {
        super.setName(name);
        super.setData(data.stream().map(item -> (ChartSeriesDataBase) item).collect(Collectors.toList()));
        super.setType(ChartType.Bar.toString().toLowerCase());
    }

    public String getBarWidth() {
        return barWidth;
    }

    public void setBarWidth(String barWidth) {
        this.barWidth = barWidth;
    }
}
