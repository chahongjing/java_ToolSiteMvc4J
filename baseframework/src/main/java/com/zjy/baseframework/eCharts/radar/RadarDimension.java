package com.zjy.baseframework.eCharts.radar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 雷达维度
/// </summary>
public class RadarDimension
{
    /// <summary>
    /// 维度信息
    /// </summary>
    public List<IndicatorData> indicator;

    /// <summary>
    /// 构造函数
    /// </summary>
    public RadarDimension()
    {
        indicator = new ArrayList<>();
    }

    public List<IndicatorData> getIndicator() {
        return indicator;
    }

    public void setIndicator(List<IndicatorData> indicator) {
        this.indicator = indicator;
    }
}