package com.zjy.baseframework.eCharts.normalDistribution;

import com.zjy.baseframework.eCharts.common.*;

import java.util.ArrayList;
import java.util.List;

public class NormalDistribution extends EChartsBase {
    private List<String> color;
    public NormalDistribution(ChartTitle title) {
        this(title, new ArrayList<>(), new ChartToolTip());
    }

    public NormalDistribution(ChartTitle title, List<ChartSeriesBase> series, ChartToolTip tooltip) {
        super(title, series, tooltip);
        super.setGrid(new Grid());
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }
}
