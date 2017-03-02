package com.zjy.baseframework.eCharts.dashBoard;

import java.util.List;

/**
 * 仪表盘指针样式
 */
public class DashBoardSeriesAxisLineLineStyle
{
    /**
     * 每个阶段颜色(比如0-30一个颜色, 30-70一个颜色, 70-100一个颜色)
     */
    private List<List<Object>> color;

    /**
     * 构造函数
     */
    public DashBoardSeriesAxisLineLineStyle()
    {
        //color = new List<List<object>>();
    }

    public List<List<Object>> getColor() {
        return color;
    }

    public void setColor(List<List<Object>> color) {
        this.color = color;
    }
}