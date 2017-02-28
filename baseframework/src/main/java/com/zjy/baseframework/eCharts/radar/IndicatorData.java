package com.zjy.baseframework.eCharts.radar;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 雷达维度数据
/// </summary>
public class IndicatorData
{
    /// <summary>
    /// 名称
    /// </summary>
    public String name;

    /// <summary>
    /// 最大值
    /// </summary>
    public float max ;

    /// <summary>
    /// 构造函数
    /// </summary>
    public IndicatorData()
    { this(null);}

    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="name">名称</param>
    public IndicatorData(String name)
    { this(name, 100); }

    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="name">名称</param>
    /// <param name="max">最大值</param>
    public IndicatorData(String name, float max)
    {
        this.name = name;
        this.max = max;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }
}
