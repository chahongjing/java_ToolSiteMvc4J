package com.zjy.baseframework.eCharts.dashBoard;

import com.zjy.baseframework.eCharts.common.ChartSeriesDataBase;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 仪表盘数据
/// </summary>
public class DashBoardSeriesData extends ChartSeriesDataBase {
    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="value">值</param>
    public DashBoardSeriesData(String value) {
        this(value, null);
    }

    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="value">值</param>
    /// <param name="name">名称</param>
    public DashBoardSeriesData(String value, String name) {
        super.setName(name);
        super.setValue(value);
    }
}
