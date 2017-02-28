using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ToolSiteMVC.BLL.Common
{
    /// <summary>
    /// 图表类型
    /// </summary>
    public enum ChartType
    {
        /// <summary>
        /// 拆线图
        /// </summary>
        Line,
        /// <summary>
        /// 仪表盘
        /// </summary>
        Gauge,
        /// <summary>
        /// 柱状图
        /// </summary>
        Bar,
        /// <summary>
        /// 雷达图
        /// </summary>
        Radar,
        /// <summary>
        /// 饼图
        /// </summary>
        Pie
    }

    /// <summary>
    /// x坐标位置
    /// </summary>
    public enum LabelXLocation
    {
        /// <summary>
        /// 居左
        /// </summary>
        Left,
        /// <summary>
        /// 居中
        /// </summary>
        Center,
        /// <summary>
        /// 居右
        /// </summary>
        Right

    }

    /// <summary>
    /// y坐标位置
    /// </summary>
    public enum LabelYLocation
    {
        /// <summary>
        /// 居上
        /// </summary>
        Top,
        /// <summary>
        /// 居中
        /// </summary>
        Middle,
        /// <summary>
        /// 居下
        /// </summary>
        Bottom
    }

    /// <summary>
    /// 触发类型
    /// </summary>
    public enum TriggerType
    {
        /// <summary>
        /// 坐标轴触发，主要在柱状图，折线图等会使用类目轴的图表中使用。
        /// </summary>
        Axis,
        /// <summary>
        /// 数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。
        /// </summary>
        Item
    }

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

    public enum LineType
    {
        /// <summary>
        /// 直线指示器
        /// </summary>
        Line,
        /// <summary>
        /// 十字准星指示器
        /// </summary>
        Cross,
        /// <summary>
        /// 阴影指示器
        /// </summary>
        Shadow
    }

    /// <summary>
    /// 方向
    /// </summary>
    public enum OrientType
    {
        /// <summary>
        /// 水平方向
        /// </summary>
        Horizontal,
        /// <summary>
        /// 竖直方向
        /// </summary>
        Vertical
    }
}
