package com.zjy.baseframework.eCharts.enums;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 取值类型
/// </summary>
public enum AxisValueType
{
    /// <summary>
    /// 数值轴，适用于连续数据
    /// </summary>
    Value,
    /// <summary>
    /// 类目轴，适用于离散的类目数据，为该类型时必须通过 data 设置类目数据
    /// </summary>
    Category,
    /// <summary>
    ///  时间轴，适用于连续的时序数据，与数值轴相比时间轴带有时间的格式化，在刻度计算上也有所不同，例如会根据跨度的范围来决定使用月，星期，日还是小时范围的刻度。
    /// </summary>
    Time,
    /// <summary>
    /// 对数轴。适用于对数数据。
    /// </summary>
    Log
}