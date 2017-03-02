package com.zjy.baseframework.eCharts.pie;

import com.zjy.baseframework.eCharts.common.ChartSeriesBase;
import com.zjy.baseframework.eCharts.common.ChartTitle;
import com.zjy.baseframework.eCharts.common.ChartToolTip;
import com.zjy.baseframework.eCharts.common.EChartsBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 饼图
 */
public class Pie extends EChartsBase {
    /**
     * 构造函数
     */
    public Pie() {
        this(null);
    }

    /**
     * 构造函数
     * @param title 标题
     */
    public Pie(ChartTitle title) {
        this(title, new ArrayList<>(), new ChartToolTip());
    }

    /**
     * 构造函数
     * @param title 标题
     * @param series 数据
     * @param tooltip 鼠标hover提示
     */
    public Pie(ChartTitle title, List<ChartSeriesBase> series, ChartToolTip tooltip) {
        super(title, series, tooltip);
    }
}