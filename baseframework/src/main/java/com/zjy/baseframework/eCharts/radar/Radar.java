package com.zjy.baseframework.eCharts.radar;

import com.zjy.baseframework.eCharts.common.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 雷达图
 */
public class Radar extends EChartsBase {
    /**
     * 雷达维度
     */
    private RadarDimension radar;

    /**
     * 构造函数
     */
    public Radar() {
        this(null);
    }

    /**
     * 构造函数
     * @param title 标题
     */
    public Radar(ChartTitle title) {
        this(title, new ChartToolTip());
    }

    /**
     * 构造函数
     * @param title 标题
     * @param toolTip 鼠标hover提示
     */
    public Radar(ChartTitle title, ChartToolTip toolTip) {
        this(title, toolTip, new ArrayList<>());
    }

    /**
     * 构造函数
     * @param title 标题
     * @param tooltip 鼠标hover提示
     * @param series 数据
     */
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