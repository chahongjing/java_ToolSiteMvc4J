package com.zjy.baseframework.eCharts.radar;

/**
 * 雷达维度数据
 */
public class IndicatorData
{
    /**
     * 名称
     */
    private String name;

    /**
     * 最大值
     */
    private float max ;

    /**
     * 构造函数
     */
    public IndicatorData()
    { this(null);}

    /**
     * 构造函数
     * @param name 名称
     */
    public IndicatorData(String name)
    { this(name, 100); }

    /**
     * 构造函数
     * @param name 名称
     * @param max 最大值
     */
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
