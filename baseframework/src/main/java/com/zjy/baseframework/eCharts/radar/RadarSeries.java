package com.zjy.baseframework.eCharts.radar;

import com.zjy.baseframework.eCharts.common.ChartSeriesBase;
import com.zjy.baseframework.eCharts.common.ChartSeriesDataBase;
import com.zjy.baseframework.eCharts.enums.ChartType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 雷达维度
/// </summary>
/// <summary>
/// 雷达数据
/// </summary>
public class RadarSeries extends ChartSeriesBase {
    /// <summary>
/// 构造函数
/// </summary>
    public RadarSeries() {
        this(null);
    }

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="name">名称</param>
    public RadarSeries(String name) {
        this(name, null);
    }

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="name">名称</param>
/// <param name="data">数据</param>
    public RadarSeries(String name, List<RadarSeriesData> data) {
        super.name = name;
        super.data = data.stream().map(item -> (ChartSeriesDataBase) item).collect(Collectors.toList());
        super.type = ChartType.Radar.toString().toLowerCase();
    }
}