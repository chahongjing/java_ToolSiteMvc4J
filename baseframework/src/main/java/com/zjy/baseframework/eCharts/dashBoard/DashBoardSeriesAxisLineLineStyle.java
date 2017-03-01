package com.zjy.baseframework.eCharts.dashBoard;

import java.util.List;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 仪表盘指针样式
/// </summary>
public class DashBoardSeriesAxisLineLineStyle
{
    /// <summary>
    /// 每个阶段颜色(比如0-30一个颜色, 30-70一个颜色, 70-100一个颜色)
    /// </summary>
    private List<List<Object>> color;

    /// <summary>
    /// 构造函数
    /// </summary>
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