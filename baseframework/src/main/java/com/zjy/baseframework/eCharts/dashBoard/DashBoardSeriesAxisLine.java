package com.zjy.baseframework.eCharts.dashBoard;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 仪表盘指针
/// </summary>
public class DashBoardSeriesAxisLine
{
    /// <summary>
    /// 仪表盘指针样式
    /// </summary>
    private DashBoardSeriesAxisLineLineStyle lineStyle;

    /// <summary>
    /// 构造函数
    /// </summary>
    public DashBoardSeriesAxisLine()
    {
        lineStyle = new DashBoardSeriesAxisLineLineStyle();
    }

    public DashBoardSeriesAxisLineLineStyle getLineStyle() {
        return lineStyle;
    }

    public void setLineStyle(DashBoardSeriesAxisLineLineStyle lineStyle) {
        this.lineStyle = lineStyle;
    }
}