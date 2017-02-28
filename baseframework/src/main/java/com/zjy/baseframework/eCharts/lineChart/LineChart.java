package com.zjy.baseframework.eCharts.lineChart;

import com.zjy.baseframework.eCharts.common.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 折线表
/// </summary>
public class LineChart extends EChartsBase {
    /// <summary>
/// 构造函数
/// </summary>
    public LineChart() {
        this(null);
    }

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="title">标题</param>
    public LineChart(ChartTitle title) {
        this(title, new ArrayList<>(), new ChartToolTip());
    }

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="title">标题</param>
/// <param name="series">数据</param>
/// <param name="tooltip">鼠标hover提示</param>
    public LineChart(ChartTitle title, List<ChartSeriesBase> series, ChartToolTip tooltip) {
        super(title, series, tooltip);
        super.grid = new Grid();
    }
}