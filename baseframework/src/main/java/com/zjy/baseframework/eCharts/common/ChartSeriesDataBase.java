package com.zjy.baseframework.eCharts.common;

/**
 * 系列列表数据
 */
public class ChartSeriesDataBase
{
    /**
     * 名称
     */
    private String name;
    /**
     * 节点样式，如圆圈，方块
     */
    private String symbol;
    /**
     * 节点样式，如圆圈，方块
     */
    private Integer symbolSize;

    /**
     * 值
     */
    private Object value;

    /**
     * 指针,标识颜色
     */
    private ItemStyle itemStyle;

    /**
     * 构造函数
     */
    public ChartSeriesDataBase()
    { }

    /**
     * 构造函数
     * @param name 名称
     */
    public ChartSeriesDataBase(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public ItemStyle getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getSymbolSize() {
        return symbolSize;
    }

    public void setSymbolSize(Integer symbolSize) {
        this.symbolSize = symbolSize;
    }
}