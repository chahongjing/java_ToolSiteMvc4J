package com.zjy.baseframework.eCharts.radar;

import java.util.ArrayList;
import java.util.List;

/**
 * 雷达维度
 */
public class RadarDimension
{
    /**
     * 维度信息
     */
    private List<IndicatorData> indicator;

    /**
     * 构造函数
     */
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