package com.zjy.baseframework.eCharts.dashBoard;

import com.zjy.baseframework.eCharts.common.ChartSeriesDataBase;

/**
 * 仪表盘数据
 */
public class DashBoardSeriesData extends ChartSeriesDataBase {
    /**
     * 构造函数
     * @param value 值
     */
    public DashBoardSeriesData(String value) {
        this(value, null);
    }

    /**
     * 构造函数
     * @param value 值
     * @param name 名称
     */
    public DashBoardSeriesData(String value, String name) {
        super.setName(name);
        super.setValue(value);
    }
}
