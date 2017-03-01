package com.zjy.baseframework.eCharts.common;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 标题数据
/// </summary>
public class LegendData
{
    /// <summary>
    /// 名称
    /// </summary>
    private String name;

    /// <summary>
    /// 图标
    /// </summary>
    private String icon;

    /// <summary>
    /// 样式
    /// </summary>
    private Object textStyle;

    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="name">名称</param>
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
