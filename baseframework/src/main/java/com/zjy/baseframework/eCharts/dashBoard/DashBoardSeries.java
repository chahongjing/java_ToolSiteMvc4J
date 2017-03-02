package com.zjy.baseframework.eCharts.dashBoard;

import com.zjy.baseframework.eCharts.common.AxisLabel;
import com.zjy.baseframework.eCharts.common.ChartSeriesBase;
import com.zjy.baseframework.eCharts.common.ChartSeriesDataBase;
import com.zjy.baseframework.eCharts.enums.ChartType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据
 */
public class DashBoardSeries extends ChartSeriesBase {
    /**
     * 详情
     */
    private DashBoardSeriesDetail detail;

/// <summary>
/// 轴线
/// </summary>
//public DashBoardSeriesAxisLine axisLine { get; set; }

    /**
     * 轴标
     */
    private AxisLabel axisLabel;

    /**
     * 构造函数
     * @param name 名称
     */
    public DashBoardSeries(String name) {
        this(name, null);
    }

    /**
     * 构造函数
     * @param name 名称
     * @param data 数据
     */
    public DashBoardSeries(String name, List<DashBoardSeriesData> data) {
        super.setType(ChartType.Gauge.toString().toLowerCase());
        //axisLine = new DashBoardSeriesAxisLine();
        axisLabel = new AxisLabel();
        super.setData(data.stream().map(item -> (ChartSeriesDataBase) item).collect(Collectors.toList()));
        super.setName(name);
    }

    public DashBoardSeriesDetail getDetail() {
        return detail;
    }

    public void setDetail(DashBoardSeriesDetail detail) {
        this.detail = detail;
    }

    public AxisLabel getAxisLabel() {
        return axisLabel;
    }

    public void setAxisLabel(AxisLabel axisLabel) {
        this.axisLabel = axisLabel;
    }
}
