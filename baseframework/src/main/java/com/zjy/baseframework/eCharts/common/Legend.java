package com.zjy.baseframework.eCharts.common;

import com.zjy.baseframework.eCharts.enums.LabelXLocation;
import com.zjy.baseframework.eCharts.enums.OrientType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 标题
/// </summary>
public class Legend
{
    /// <summary>
    /// 是否显示
    /// </summary>
    private Boolean show;

    /// <summary>
    /// 源
    /// </summary>
    private String orient;

    /// <summary>
    /// 左
    /// </summary>
    private String left;
    /// <summary>
    /// 标题数据
    /// </summary>
    private List<LegendData> data;

    /// <summary>
    /// 构造函数
    /// </summary>
    public Legend()
    { this(new ArrayList<>());}

    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="data">标题数据</param>
    public Legend(List<LegendData> data)
    {
        show = true;
        this.data = data;
        orient = OrientType.Horizontal.toString().toLowerCase();
        left = LabelXLocation.Center.toString().toLowerCase();
    }
}
