package com.zjy.baseframework.eCharts.common;

import java.util.List;

/**
 * 图表系列
 */
public class ChartSeriesBase {
    /**
     * 名称
     */
    private String name;
    /**
     * 数据
     */
    private List<ChartSeriesDataBase> data;
    /**
     * 类型(line, Gauge)
     */
    private String type;

    /**
     * 构造函数
     */
    public ChartSeriesBase() {
        this(null);
    }

    /**
     * 构造函数
     *
     * @param name 名称
     */
    public ChartSeriesBase(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChartSeriesDataBase> getData() {
        return data;
    }

    public void setData(List<ChartSeriesDataBase> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
