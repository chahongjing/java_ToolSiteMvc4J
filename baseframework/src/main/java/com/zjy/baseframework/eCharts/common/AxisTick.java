package com.zjy.baseframework.eCharts.common;

/**
 * 轴刻度线
 */
public class AxisTick {
    /**
     * 刻度线和标签对齐
     */
    private Boolean alignWithLabel;

    /**
     * 构造函数
     */
    public AxisTick() {
        this(false);
    }

    /***
     * 构造函数
     * @param isAlignWithLabel 是否对齐
     */
    public AxisTick(Boolean isAlignWithLabel) {
        alignWithLabel = isAlignWithLabel;
    }

    public Boolean getAlignWithLabel() {
        return alignWithLabel;
    }

    public void setAlignWithLabel(Boolean alignWithLabel) {
        this.alignWithLabel = alignWithLabel;
    }
}
