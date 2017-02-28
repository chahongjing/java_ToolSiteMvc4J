package com.zjy.baseframework.eCharts.histogram;

import com.zjy.baseframework.eCharts.common.ChartSeriesBase;
import com.zjy.baseframework.eCharts.common.ChartSeriesDataBase;
import com.zjy.baseframework.eCharts.enums.ChartType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 柱状图数据
/// </summary>
public class HistogramSeries extends ChartSeriesBase {
    /// <summary>
/// 宽度
/// </summary>
    public String barWidth;

    /// <summary>
/// 构造函数
/// </summary>
    public HistogramSeries() {
        this(null);
    }

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="name">名称</param>
    public HistogramSeries(String name) {
        this(name, null);
    }

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="name">名称</param>
/// <param name="data">值</param>
    public HistogramSeries(String name, List<HistogramSeriesData> data) {
        super.name = name;
        super.data = data.stream().map(item -> (ChartSeriesDataBase) item).collect(Collectors.toList());
        super.type = ChartType.Bar.toString().toLowerCase();
    }

    public String getBarWidth() {
        return barWidth;
    }

    public void setBarWidth(String barWidth) {
        this.barWidth = barWidth;
    }
}
