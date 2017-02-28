package com.zjy.baseframework.eCharts.common;

import com.zjy.baseframework.eCharts.enums.AxisValueType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// x轴
/// </summary>
public class XAxis
{
    /// <summary>
    /// 标题
    /// </summary>
    public String name;

    /// <summary>
    /// 类型
    /// </summary>
    public String type;

    /// <summary>
    /// 坐标轴两边留白策略
    /// </summary>
    public Boolean boundaryGap;

    /// <summary>
    /// 数据
    /// </summary>
    public List<String> data;

    /// <summary>
    /// 轴标信息
    /// </summary>
    public AxisLabel axisLabel;

    /// <summary>
    /// 刻度
    /// </summary>
    public AxisTick axisTick;

    /// <summary>
    /// 标题间隔
    /// </summary>
    public int nameGap;

    /// <summary>
    /// 标题旋转
    /// </summary>
    public int nameRotate;

    /// <summary>
    /// 标题位置
    /// </summary>
    public String nameLocation;

    /// <summary>
    /// 构造函数
    /// </summary>
    public XAxis()
    { this(null);}
    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="name">名称</param>
    public XAxis(String name)
    { this(name, AxisValueType.Value);}
    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="name">名称</param>
    /// <param name="data">值</param>
    public XAxis(String name, List<String> data)
    { this(name, AxisValueType.Category, data);}
    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="name">名称</param>
    /// <param name="type">取值类型</param>
    public XAxis(String name, AxisValueType type)
    {this(name, type, new ArrayList<>()); }
    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="name">名称</param>
    /// <param name="type">取值类型</param>
    /// <param name="data">值</param>
    public XAxis(String name, AxisValueType type, List<String> data)
    {
        this.name = name;
        this.type = type.toString().toLowerCase();
        this.data = data;
        boundaryGap = false;
        axisLabel = new AxisLabel();
        nameLocation = "end";
        nameGap = 5;
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

    public Boolean getBoundaryGap() {
        return boundaryGap;
    }

    public void setBoundaryGap(Boolean boundaryGap) {
        this.boundaryGap = boundaryGap;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public AxisLabel getAxisLabel() {
        return axisLabel;
    }

    public void setAxisLabel(AxisLabel axisLabel) {
        this.axisLabel = axisLabel;
    }

    public AxisTick getAxisTick() {
        return axisTick;
    }

    public void setAxisTick(AxisTick axisTick) {
        this.axisTick = axisTick;
    }

    public int getNameGap() {
        return nameGap;
    }

    public void setNameGap(int nameGap) {
        this.nameGap = nameGap;
    }

    public int getNameRotate() {
        return nameRotate;
    }

    public void setNameRotate(int nameRotate) {
        this.nameRotate = nameRotate;
    }

    public String getNameLocation() {
        return nameLocation;
    }

    public void setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
    }
}