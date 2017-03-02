package com.zjy.baseframework.eCharts.histogram;

import com.zjy.baseframework.eCharts.common.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 柱状图
 */
public class Histogram extends EChartsBase {
    /**
     * 颜色
     */
    private List<String> color;

    /**
     * 构造函数
     */
    public Histogram() {
        this(null);
    }

    /**
     * 构造函数
     * @param title 标题
     */
    public Histogram(ChartTitle title) {
        this(title, new ArrayList<>(), new ChartToolTip());
    }

    /**
     * 构造函数
     * @param title 标题
     * @param series 数据
     * @param tooltip 鼠标hover提示
     */
    public Histogram(ChartTitle title, List<ChartSeriesBase> series, ChartToolTip tooltip) {
        super(title, series, tooltip);
        color = EChartsBase.Colors;
        super.setGrid(new Grid());
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }
}
