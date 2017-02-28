package com.zjy.baseframework.eCharts.dashBoard;

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
/// 仪表盘
/// </summary>
public class DashBoard extends EChartsBase {
    /// <summary>
/// 构造函数
/// </summary>
    public DashBoard() {
        this(null);
    }

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="title">标题</param>
    public DashBoard(ChartTitle title) {
        this(title, new ArrayList<>(), new ChartToolTip());
    }

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="title">标题</param>
/// <param name="series">数据</param>
/// <param name="tooltip">鼠标hover提示</param>
    public DashBoard(ChartTitle title, List<ChartSeriesBase> series, ChartToolTip tooltip) {
        super(title, series, tooltip);
    }

    /// <summary>
/// 重写tostring, 设置最后一个指针的颜色
/// </summary>
/// <returns></returns>
    @Override
    public String ToString() {
        //if (this.series.Any())
        //{
        //    var it = this.series.Last() as com.zjy.baseframework.eCharts.dashBoard.DashBoardSeries;
        //    it.axisLine.lineStyle.color.ForEach(innerItem => innerItem[1] = Colors.Last());
        //}
        return super.ToString();
    }
}