package com.zjy.baseframework.eCharts.lineChart;

import com.zjy.baseframework.eCharts.common.ChartSeriesBase;
import com.zjy.baseframework.eCharts.common.ChartSeriesDataBase;
import com.zjy.baseframework.eCharts.enums.ChartType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 折线数据
/// </summary>
public class LineChartSeries extends ChartSeriesBase {

    /// <summary>
/// 构造函数
/// </summary>
    public LineChartSeries() {
        this(null);
    }

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="name">名称</param>
    public LineChartSeries(String name) {
        this(name, null);
    }

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="name">拆线名称</param>
/// <param name="data">值</param>
    public LineChartSeries(String name, List<LineChartSeriesData> data) {
        super.setName(name);
        super.setData(data.stream().map(item -> (ChartSeriesDataBase) item).collect(Collectors.toList()));
        super.setType(ChartType.Line.toString().toLowerCase());
    }

}