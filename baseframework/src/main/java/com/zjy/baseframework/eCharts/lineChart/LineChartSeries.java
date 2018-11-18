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
    private boolean showSymbol;
    private boolean smooth;
    private Float xAxisIndex;
    private Float yAxisIndex;
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
        this.showSymbol = true;
    }

    public boolean getShowSymbol() {
        return showSymbol;
    }

    public void setShowSymbol(boolean showSymbol) {
        this.showSymbol = showSymbol;
    }

    public boolean getSmooth() {
        return smooth;
    }

    public void setSmooth(boolean smooth) {
        this.smooth = smooth;
    }

    public Float getxAxisIndex() {
        return xAxisIndex;
    }

    public void setxAxisIndex(Float xAxisIndex) {
        this.xAxisIndex = xAxisIndex;
    }

    public Float getyAxisIndex() {
        return yAxisIndex;
    }

    public void setyAxisIndex(Float yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
    }
}