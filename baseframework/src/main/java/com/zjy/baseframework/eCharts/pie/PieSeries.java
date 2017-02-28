package com.zjy.baseframework.eCharts.pie;

import com.zjy.baseframework.eCharts.common.ChartSeriesBase;
import com.zjy.baseframework.eCharts.common.ChartSeriesDataBase;
import com.zjy.baseframework.eCharts.common.ItemStyle;
import com.zjy.baseframework.eCharts.enums.ChartType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 饼图系列
/// </summary>
public class PieSeries extends ChartSeriesBase {
    /// <summary>
/// 饼图半径
/// </summary>
    public String radius;

    /// <summary>
/// 饼图的中心（圆心）坐标
/// </summary>
    public List<String> center;

    /// <summary>
/// 悬浮状态
/// </summary>
    public ItemStyle itemStyle;

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="name">名称</param>
    public PieSeries(String name) {
        this(name, null);
    }

    /// <summary>
/// 构造函数
/// </summary>
/// <param name="name">名称</param>
/// <param name="data">数据</param>
    public PieSeries(String name, List<PieSeriesData> data) {
        type = ChartType.Pie.toString().toLowerCase();
        super.data = data.stream().map(item -> (ChartSeriesDataBase) item).collect(Collectors.toList());
        this.name = name;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public List<String> getCenter() {
        return center;
    }

    public void setCenter(List<String> center) {
        this.center = center;
    }

    public ItemStyle getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
    }
}
