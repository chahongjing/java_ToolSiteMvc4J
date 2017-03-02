package com.zjy.baseframework.eCharts.common;

import com.zjy.baseframework.eCharts.enums.TriggerType;

/**
 * 鼠标悬浮提示
 */
public class ChartToolTip
{
    /**
     * 是否显示
     */
    private Boolean show;
    /**
     * 格式化器
     */
    private String formatter;

    /**
     * 触发器
     */
    private String trigger;

    /**
     * 坐标轴指示器(默认为直线)
     */
    private AxisPointer axisPointer;


    /**
     * 构造函数
     */
    public ChartToolTip()
    {this((String)null); }

    /**
     * 构造函数
     * @param formatter 格式化器
     */
    public ChartToolTip(String formatter)
    { this(formatter, TriggerType.Item);}

    /**
     * 构造函数
     * @param trigger 触发类型
     */
    public ChartToolTip(TriggerType trigger)
    { this(null, trigger); }

    /**
     * 构造函数
     * @param formatter 格式化器
     * @param trigger 触发器
     */
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
