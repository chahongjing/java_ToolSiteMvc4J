package com.zjy.baseframework.eCharts.common;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 悬浮效果
/// </summary>
public class Emphasis
{
    /// <summary>
    /// 图形阴影的模糊大小
    /// </summary>
    private int shadowBlur;

    /// <summary>
    /// 阴影水平方向上的偏移距离。
    /// </summary>
    private int shadowOffsetX;

    /// <summary>
    /// 阴影颜色
    /// </summary>
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
