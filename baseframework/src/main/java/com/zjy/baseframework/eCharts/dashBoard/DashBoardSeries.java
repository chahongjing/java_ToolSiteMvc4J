package com.zjy.baseframework.eCharts.dashBoard;

import com.zjy.baseframework.eCharts.common.AxisLabel;
import com.zjy.baseframework.eCharts.common.ChartSeriesBase;
import com.zjy.baseframework.eCharts.common.ChartSeriesDataBase;
import com.zjy.baseframework.eCharts.enums.ChartType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 数据
/// </summary>
public class DashBoardSeries extends ChartSeriesBase {
    /// <summary>
/// 详情
/// </summary>
    public DashBoardSeriesDetail detail;

/// <summary>
/// 轴线
/// </summary>
//public DashBoardSeriesAxisLine axisLine { get; set; }

    /// <summary>
/// 轴标
/// </summary>
    public AxisLabel axisLabel;

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="name">名称</param>
    public DashBoardSeries(String name) {
        this(name, null);
    }

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="name">名称</param>
/// <param name="data">数据</param>
    public DashBoardSeries(String name, List<DashBoardSeriesData> data) {
        type = ChartType.Gauge.toString().toLowerCase();
        //axisLine = new DashBoardSeriesAxisLine();
        axisLabel = new AxisLabel();
        super.data = data.stream().map(item -> (ChartSeriesDataBase) item).collect(Collectors.toList());
        this.name = name;
    }
}
