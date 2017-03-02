package com.zjy.baseframework.eCharts.pie;

import com.zjy.baseframework.eCharts.common.ChartSeriesBase;
import com.zjy.baseframework.eCharts.common.ChartSeriesDataBase;
import com.zjy.baseframework.eCharts.common.ItemStyle;
import com.zjy.baseframework.eCharts.enums.ChartType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 饼图系列
 */
public class PieSeries extends ChartSeriesBase {
    /**
     * 饼图半径
     */
    private String radius;

    /**
     * 饼图的中心（圆心）坐标
     */
    private List<String> center;

    /**
     * 悬浮状态
     */
    private ItemStyle itemStyle;

    /**
     * 构造函数
     * @param name 名称
     */
    public PieSeries(String name) {
        this(name, null);
    }

    /**
     * 构造函数
     * @param name 名称
     * @param data 数据
     */
    public PieSeries(String name, List<PieSeriesData> data) {
        super.setType(ChartType.Pie.toString().toLowerCase());
        super.setData(data.stream().map(item -> (ChartSeriesDataBase) item).collect(Collectors.toList()));
        super.setName(name);
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
