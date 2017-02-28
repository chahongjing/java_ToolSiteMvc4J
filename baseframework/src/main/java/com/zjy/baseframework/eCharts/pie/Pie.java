package com.zjy.baseframework.eCharts.pie;

import com.zjy.baseframework.eCharts.common.ChartSeriesBase;
import com.zjy.baseframework.eCharts.common.ChartTitle;
import com.zjy.baseframework.eCharts.common.ChartToolTip;
import com.zjy.baseframework.eCharts.common.EChartsBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 饼图
/// </summary>
public class Pie extends EChartsBase {
    /// <summary>
/// 构造函数
/// </summary>
    public Pie() {
        this(null);
    }

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="title">标题</param>
    public Pie(ChartTitle title) {
        this(title, new ArrayList<>(), new ChartToolTip());
    }

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="title">标题</param>
/// <param name="series">数据</param>
/// <param name="tooltip">鼠标hover提示</param>
    public Pie(ChartTitle title, List<ChartSeriesBase> series, ChartToolTip tooltip) {
        super(title, series, tooltip);
    }
}