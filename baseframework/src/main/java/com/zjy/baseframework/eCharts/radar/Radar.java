package com.zjy.baseframework.eCharts.radar;

import com.zjy.baseframework.eCharts.common.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 雷达图
/// </summary>
public class Radar extends EChartsBase {
    /// <summary>
/// 雷达维度
/// </summary>
    private RadarDimension radar;

    /// <summary>
/// 构造函数
/// </summary>
    public Radar() {
        this(null);
    }

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="title">标题</param>
    public Radar(ChartTitle title) {
        this(title, new ChartToolTip());
    }

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="title">标题</param>
/// <param name="toolTip">鼠标提示</param>
    public Radar(ChartTitle title, ChartToolTip toolTip) {
        this(title, toolTip, new ArrayList<>());
    }

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="title">标题</param>
/// <param name="tooltip">鼠标hover提示</param>
/// <param name="series">数据</param>
    public Radar(ChartTitle title, ChartToolTip tooltip, List<ChartSeriesBase> series) {
        super(title, series, tooltip);
        radar = new RadarDimension();
        super.setLegend(new Legend());
        super.setGrid(new Grid());
    }

    public RadarDimension getRadar() {
        return radar;
    }

    public void setRadar(RadarDimension radar) {
        this.radar = radar;
    }
}