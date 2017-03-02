package com.zjy.baseframework.eCharts.dashBoard;

import com.zjy.baseframework.eCharts.common.ChartSeriesBase;
import com.zjy.baseframework.eCharts.common.ChartTitle;
import com.zjy.baseframework.eCharts.common.ChartToolTip;
import com.zjy.baseframework.eCharts.common.EChartsBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 仪表盘
 */
public class DashBoard extends EChartsBase {

    /**
     * 构造函数
     */
    public DashBoard() {
        this(null);
    }

    /**
     * 构造函数
     * @param title 标题
     */
    public DashBoard(ChartTitle title) {
        this(title, new ArrayList<>(), new ChartToolTip());
    }

    /**
     * 构造函数
     * @param title 标题
     * @param series 数据
     * @param tooltip 鼠标hover提示
     */
    public DashBoard(ChartTitle title, List<ChartSeriesBase> series, ChartToolTip tooltip) {
        super(title, series, tooltip);
    }

    /**
     * 重写tostring, 设置最后一个指针的颜色
     * @return
     */
    @Override
    public String toString() {
        //if (this.series.Any())
        //{
        //    var it = this.series.Last() as com.zjy.baseframework.eCharts.dashBoard.DashBoardSeries;
        //    it.axisLine.lineStyle.color.ForEach(innerItem => innerItem[1] = Colors.Last());
        //}
        return super.toString();
    }
}