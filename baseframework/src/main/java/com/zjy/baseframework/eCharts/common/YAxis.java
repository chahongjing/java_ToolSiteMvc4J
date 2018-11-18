package com.zjy.baseframework.eCharts.common;

import com.zjy.baseframework.eCharts.enums.AxisValueType;

import java.util.List;

/**
 * y轴
 */
public class YAxis
{
    /**
     * y轴标题
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 轴信息
     */
    private AxisLabel axisLabel;

    /**
     * 分段数(如分5段)
     */
    private Float splitNumber;

    /**
     * 分段数(如分5段)
     */
    private SplitLine splitLine;

    /**
     * 每段间隔(如每段20度)
     */
    private Float interval;

    /**
     * 总值(如20 * 5 = 100度)
     */
    private Float max;

    /**
     * 总值(如20 * 5 = 100度)
     */
    private Float min;

    /**
     * 总值(如20 * 5 = 100度)
     */
    private Float minInterval;
    /**
     * 数据
     */
    private List<String> data;

    /**
     * 构造函数
     */
    public YAxis()
    { this(null);}

    /**
     * 构造函数
     * @param name 名称
     */
    public YAxis(String name)
    {this(name, AxisValueType.Value); }

    /**
     * 构造函数
     * @param name 名称
     * @param type 取值类型
     */
    public YAxis(String name, AxisValueType type)
    {this(name, type, 10f, 10f, 100f); }

    /**
     * 构造函数
     * @param name 名称
     * @param splitNumber 分隔数
     * @param interval 间隔
     * @param max 最大值
     */
    public YAxis(String name, Float splitNumber, Float interval, Float max)
    { this(name, AxisValueType.Value, splitNumber, interval, max);}

    /**
     * 构造函数
     * @param name 名称
     *  @param type 取值类型
     * @param splitNumber 分隔数
     * @param interval 间隔
     * @param max 最大值
     */
    public YAxis(String name, AxisValueType type, Float splitNumber, Float interval, Float max)
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

    public Float getSplitNumber() {
        return splitNumber;
    }

    public void setSplitNumber(Float splitNumber) {
        this.splitNumber = splitNumber;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public SplitLine getSplitLine() {
        return splitLine;
    }

    public void setSplitLine(SplitLine splitLine) {
        this.splitLine = splitLine;
    }

    public Float getInterval() {
        return interval;
    }

    public void setInterval(Float interval) {
        this.interval = interval;
    }

    public Float getMax() {
        return max;
    }

    public void setMax(Float max) {
        this.max = max;
    }

    public Float getMin() {
        return min;
    }

    public void setMin(Float min) {
        this.min = min;
    }

    public Float getMinInterval() {
        return minInterval;
    }

    public void setMinInterval(Float minInterval) {
        this.minInterval = minInterval;
    }
}