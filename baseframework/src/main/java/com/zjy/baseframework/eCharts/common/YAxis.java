package com.zjy.baseframework.eCharts.common;

import com.zjy.baseframework.eCharts.enums.AxisValueType;

import java.util.List;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// y轴
/// </summary>
public class YAxis
{
    /// <summary>
    /// y轴标题
    /// </summary>
    private String name;

    /// <summary>
    /// 类型
    /// </summary>
    private String type;

    /// <summary>
    /// 轴信息
    /// </summary>
    private AxisLabel axisLabel;

    /// <summary>
    /// 分段数(如分5段)
    /// </summary>
    private float splitNumber;

    /// <summary>
    /// 每段间隔(如每段20度)
    /// </summary>
    private float interval;

    /// <summary>
    /// 总值(如20 * 5 = 100度)
    /// </summary>
    private float max;
    /// <summary>
    /// 数据
    /// </summary>
    private List<String> data;

    /// <summary>
    /// 构造函数
    /// </summary>
    public YAxis()
    { this(null);}
    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="name">名称</param>
    public YAxis(String name)
    {this(name, AxisValueType.Value); }

    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="name">名称</param>
    /// <param name="type">取值类型</param>
    public YAxis(String name, AxisValueType type)
    {this(name, type, 10, 10, 100); }

    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="name">名称</param>
    /// <param name="splitNumber">分隔数</param>
    /// <param name="interval">间隔</param>
    /// <param name="max">最大值</param>
    public YAxis(String name, int splitNumber, int interval, int max)
    { this(name, AxisValueType.Value, splitNumber, interval, max);}

    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="name">名称</param>
    /// <param name="type">取值类型</param>
    /// <param name="splitNumber">分隔数</param>
    /// <param name="interval">间隔</param>
    /// <param name="max">最大值</param>
    public YAxis(String name, AxisValueType type, int splitNumber, int interval, int max)
    {
        this.name = name;
        this.type = type.toString().toLowerCase();
        axisLabel = new AxisLabel();
        this.splitNumber = splitNumber;
        this.interval = interval;
        this.max = max;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AxisLabel getAxisLabel() {
        return axisLabel;
    }

    public void setAxisLabel(AxisLabel axisLabel) {
        this.axisLabel = axisLabel;
    }

    public float getSplitNumber() {
        return splitNumber;
    }

    public void setSplitNumber(float splitNumber) {
        this.splitNumber = splitNumber;
    }

    public float getInterval() {
        return interval;
    }

    public void setInterval(float interval) {
        this.interval = interval;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}