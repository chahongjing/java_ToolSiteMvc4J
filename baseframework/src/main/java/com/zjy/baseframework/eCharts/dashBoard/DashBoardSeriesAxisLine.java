package com.zjy.baseframework.eCharts.dashBoard;

/**
 * 仪表盘指针
 */
public class DashBoardSeriesAxisLine
{
    /**
     * 仪表盘指针样式
     */
    private DashBoardSeriesAxisLineLineStyle lineStyle;

    /**
     * 构造函数
     */
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