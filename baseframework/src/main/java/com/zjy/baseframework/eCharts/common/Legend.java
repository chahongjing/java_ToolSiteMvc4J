package com.zjy.baseframework.eCharts.common;

import com.zjy.baseframework.eCharts.enums.LabelXLocation;
import com.zjy.baseframework.eCharts.enums.OrientType;

import java.util.ArrayList;
import java.util.List;

/**
 * 标题
 */
public class Legend
{
    /**
     * 是否显示
     */
    private Boolean show;

    /**
     * 源
     */
    private String orient;

    /**
     * 左
     */
    private String left;
    /**
     * 标题数据
     */
    private List<LegendData> data;

    /**
     * 构造函数
     */
    public Legend()
    { this(new ArrayList<>());}

    /**
     * 构造函数
     * @param data 标题数据
     */
    public Legend(List<LegendData> data)
    {
        show = true;
        this.data = data;
        orient = OrientType.Horizontal.toString().toLowerCase();
        left = LabelXLocation.Center.toString().toLowerCase();
    }
}
