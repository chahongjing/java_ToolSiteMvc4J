package com.zjy.baseframework.eCharts.common;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 图表基类
 */
public class EChartsBase
{
    /**
     * 指针颜色
     */
    public static List<String> Colors = new ArrayList<>();

    static {
        Colors.add("#c23531");
        Colors.add("#2f4554");
        Colors.add("#d48265");
        Colors.add("#91c7ae");
        Colors.add("#749f83");

        Colors.add("#ca8622");
        Colors.add("#bda29a");
        Colors.add("#6e7074");
        Colors.add("#546570");
        Colors.add("#c4ccd3");
        Colors.add("#3398DB");
        Colors.add("#61a0a8");
    }

    /**
     * 图表标题
     */
    private ChartTitle title;

    /**
     * 鼠标悬浮提示
     */
    private ChartToolTip tooltip;

    /**
     * 标题
     */
    private Legend legend;

    /**
     * 网格
     */
    private Grid grid;

    /**
     * 数据
     */
    private List<ChartSeriesBase> series;

    /**
     * x轴
     */
    private List<XAxis> xAxis;

    /**
     * y轴
     */
    private List<YAxis> yAxis;

    /**
     * 构造函数
     */
    public EChartsBase()
    { this(null);}

    /**
     * 构造函数
     * @param title 标题
     */
    public EChartsBase(ChartTitle title)
    { this(title, new ArrayList<>(), new ChartToolTip());}

    /**
     * 构造函数
     * @param title 标题
     * @param series 数据
     * @param tooltip 鼠标hover提示
     */
    public EChartsBase(ChartTitle title, List<ChartSeriesBase> series, ChartToolTip tooltip)
    {
        this.title = title;
        this.series = series;
        this.tooltip = tooltip;
    }

    /**
     * toString(获取json字符串)
     * @return
     */
    public String toString()
{
    return JSON.toJSONString(this);
}

    public static List<String> getColors() {
        return Colors;
    }

    public static void setColors(List<String> colors) {
        Colors = colors;
    }

    public ChartTitle getTitle() {
        return title;
    }

    public void setTitle(ChartTitle title) {
        this.title = title;
    }

    public ChartToolTip getTooltip() {
        return tooltip;
    }

    public void setTooltip(ChartToolTip tooltip) {
        this.tooltip = tooltip;
    }

    public Legend getLegend() {
        return legend;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public List<ChartSeriesBase> getSeries() {
        return series;
    }

    public void setSeries(List<ChartSeriesBase> series) {
        this.series = series;
    }

    public List<XAxis> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<XAxis> xAxis) {
        this.xAxis = xAxis;
    }

    public List<YAxis> getyAxis() {
        return yAxis;
    }

    public void setyAxis(List<YAxis> yAxis) {
        this.yAxis = yAxis;
    }
}
