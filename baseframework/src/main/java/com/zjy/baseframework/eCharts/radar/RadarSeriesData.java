package com.zjy.baseframework.eCharts.radar;

import com.zjy.baseframework.eCharts.common.ChartSeriesDataBase;

import java.util.List;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 仪表盘数据
/// </summary>
public class RadarSeriesData extends ChartSeriesDataBase {
    /// <summary>
/// 构造函数
/// </summary>
/// <param name="value">值</param>
    public RadarSeriesData(List<Float> value) {
        this(value, null);
    }

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="value">值</param>
/// <param name="name">名称</param>
    public RadarSeriesData(List<Float> value, String name) {
        super.setName(name);
        super.setValue(value);
    }
}