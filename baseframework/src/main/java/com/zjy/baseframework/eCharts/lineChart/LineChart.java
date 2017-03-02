package com.zjy.baseframework.eCharts.lineChart;

import com.zjy.baseframework.eCharts.common.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 折线表
 */
public class LineChart extends EChartsBase {
    /**
     * 构造函数
     */
    public LineChart() {
        this(null);
    }

    /**
     * 构造函数
     * @param title 标题
     */
    public LineChart(ChartTitle title) {
        this(title, new ArrayList<>(), new ChartToolTip());
    }

    /**
     * 构造函数
     * @param title 标题
     * @param series 数据
     * @param tooltip 鼠标hover提示
     */
    public LineChart(ChartTitle title, List<ChartSeriesBase> series, ChartToolTip tooltip) {
        super(title, series, tooltip);
        super.setGrid(new Grid());
    }
}