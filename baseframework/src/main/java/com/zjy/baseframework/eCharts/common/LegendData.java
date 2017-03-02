package com.zjy.baseframework.eCharts.common;

/**
 * 标题数据
 */
public class LegendData
{
    /**
     * 名称
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 样式
     */
    private Object textStyle;

    /**
     * 构造函数
     * @param name 名称
     */
    public LegendData(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Object getTextStyle() {
        return textStyle;
    }

    public void setTextStyle(Object textStyle) {
        this.textStyle = textStyle;
    }
}
