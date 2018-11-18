package com.zjy.baseframework.eCharts.common;

/**
 * 常规
 */
public class ItemStyleNormal
{
    /**
     * 设置线的样式，如颜色
     */
    private LineStyle lineStyle;
    /**
     * 颜色
     */
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LineStyle getLineStyle() {
        return lineStyle;
    }

    public void setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
    }
}
