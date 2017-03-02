package com.zjy.baseframework.eCharts.lineChart;

import com.zjy.baseframework.eCharts.common.ChartSeriesBase;
import com.zjy.baseframework.eCharts.common.ChartSeriesDataBase;
import com.zjy.baseframework.eCharts.enums.ChartType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 折线数据
 */
public class LineChartSeries extends ChartSeriesBase {

    /**
     * 构造函数
     */
    public LineChartSeries() {
        this(null);
    }

    /**
     * 构造函数
     * @param name 拆线名称
     */
    public LineChartSeries(String name) {
        this(name, null);
    }

    /**
     * 构造函数
     * @param name 拆线名称
     * @param data 值
     */
    public LineChartSeries(String name, List<LineChartSeriesData> data) {
        super.setName(name);
        super.setData(data.stream().map(item -> (ChartSeriesDataBase) item).collect(Collectors.toList()));
        super.setType(ChartType.Line.toString().toLowerCase());
    }

}