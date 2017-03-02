package com.zjy.baseframework.eCharts.common;

/**
 * 悬浮效果
 */
public class Emphasis
{
    /**
     * 图形阴影的模糊大小
     */
    private int shadowBlur;

    /**
     * 阴影水平方向上的偏移距离
     */
    private int shadowOffsetX;

    /**
     * 阴影颜色
     */
    private String shadowColor;

    public int getShadowBlur() {
        return shadowBlur;
    }

    public void setShadowBlur(int shadowBlur) {
        this.shadowBlur = shadowBlur;
    }

    public int getShadowOffsetX() {
        return shadowOffsetX;
    }

    public void setShadowOffsetX(int shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
    }

    public String getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(String shadowColor) {
        this.shadowColor = shadowColor;
    }
}
