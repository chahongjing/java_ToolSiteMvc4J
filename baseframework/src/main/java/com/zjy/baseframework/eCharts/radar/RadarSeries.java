package com.zjy.baseframework.eCharts.radar;

import com.zjy.baseframework.eCharts.common.ChartSeriesBase;
import com.zjy.baseframework.eCharts.common.ChartSeriesDataBase;
import com.zjy.baseframework.eCharts.enums.ChartType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 雷达数据
 */
public class RadarSeries extends ChartSeriesBase {
    /**
     * 构造函数
     */
    public RadarSeries() {
        this(null);
    }

    /**
     * 构造函数
     * @param name 名称
     */
    public RadarSeries(String name) {
        this(name, null);
    }

    /**
     * 构造函数
     * @param name 名称
     * @param data 数据
     */
    public RadarSeries(String name, List<RadarSeriesData> data) {
        super.setName(name);
        super.setData(data.stream().map(item -> (ChartSeriesDataBase) item).collect(Collectors.toList()));
        super.setType(ChartType.Radar.toString().toLowerCase());
    }
}