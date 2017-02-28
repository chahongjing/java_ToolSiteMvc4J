package com.zjy.baseframework.eCharts.histogram;

import com.zjy.baseframework.eCharts.common.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 柱状图
/// </summary>
public class Histogram extends EChartsBase {
    /// <summary>
/// 颜色
/// </summary>
    public List<String> color;

    /// <summary>
/// 构造函数
/// </summary>
    public Histogram() {
        this(null);
    }

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="title">标题</param>
    public Histogram(ChartTitle title) {
        this(title, new ArrayList<>(), new ChartToolTip());
    }

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="title">标题</param>
/// <param name="series">数据</param>
/// <param name="tooltip">鼠标hover提示</param>
    public Histogram(ChartTitle title, List<ChartSeriesBase> series, ChartToolTip tooltip) {
        super(title, series, tooltip);
        color = EChartsBase.Colors;
        grid = new Grid();
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }
}
