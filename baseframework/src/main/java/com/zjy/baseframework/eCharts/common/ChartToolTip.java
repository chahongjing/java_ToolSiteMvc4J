package com.zjy.baseframework.eCharts.common;

import com.zjy.baseframework.eCharts.enums.TriggerType;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 鼠标悬浮提示
/// </summary>
public class ChartToolTip
{
    /// <summary>
    /// 是否显示
    /// </summary>
    private Boolean show;
    /// <summary>
    /// 格式化器
    /// </summary>
    private String formatter;

    /// <summary>
    /// 触发器
    /// </summary>
    private String trigger;

    /// <summary>
    /// 坐标轴指示器(默认为直线)
    /// </summary>
    private AxisPointer axisPointer;


    /// <summary>
    /// 构造函数
    /// </summary>
    public ChartToolTip()
    {this((String)null); }

    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="formatter">格式化器</param>
    public ChartToolTip(String formatter)
    { this(formatter, TriggerType.Item);}

    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="trigger">触发类型</param>
    public ChartToolTip(TriggerType trigger)
    { this(null, trigger); }

    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="formatter">格式化器</param>
    /// <param name="trigger">触发器</param>
    public ChartToolTip(String formatter, TriggerType trigger)
    {
        show = true;
        this.formatter = formatter;
        axisPointer = new AxisPointer();
        this.trigger = trigger.toString().toLowerCase();
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public AxisPointer getAxisPointer() {
        return axisPointer;
    }

    public void setAxisPointer(AxisPointer axisPointer) {
        this.axisPointer = axisPointer;
    }
}
