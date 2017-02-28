using BaseCommon.Serialize;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ToolSiteMVC.BLL.Common
{
    /// <summary>
    /// 图表基类
    /// </summary>
    public class EChartsBase
    {
        /// <summary>
        /// 指针颜色
        /// </summary>
        public static readonly List<String> Colors = new List<string>() { "#c23531", "#2f4554", "#d48265", "#91c7ae", "#749f83", "#ca8622", "#bda29a", "#6e7074", "#546570", "#c4ccd3", "#3398DB", "#61a0a8" };

        /// <summary>
        /// 图表标题
        /// </summary>
        public ChartTitle title { get; set; }

        /// <summary>
        /// 鼠标悬浮提示
        /// </summary>
        public ChartToolTip tooltip { get; set; }

        /// <summary>
        /// 标题
        /// </summary>
        public Legend legend { get; set; }

        /// <summary>
        /// 网格
        /// </summary>
        public Grid grid { get; set; }

        /// <summary>
        /// 数据
        /// </summary>
        public List<ChartSeriesBase> series { get; set; }

        /// <summary>
        /// x轴
        /// </summary>
        public List<XAxis> xAxis { get; set; }

        /// <summary>
        /// y轴
        /// </summary>
        public List<YAxis> yAxis { get; set; }

        /// <summary>
        /// 构造函数
        /// </summary>
        public EChartsBase()
            : this(null)
        { }
        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="title">标题</param>
        public EChartsBase(ChartTitle title)
            : this(title, new List<ChartSeriesBase>(), new ChartToolTip())
        { }
        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="title">标题</param>
        /// <param name="series">数据</param>
        /// <param name="tooltip">鼠标hover提示</param>
        public EChartsBase(ChartTitle title, List<ChartSeriesBase> series, ChartToolTip tooltip)
        {
            this.title = title;
            this.series = series;
            this.tooltip = tooltip;
        }

        /// <summary>
        /// ToString(获取json字符串)
        /// </summary>
        /// <returns></returns>
        public override string ToString()
        {
            return JsonHelper.ToJson(this);
        }
    }

    /// <summary>
    /// 标题
    /// </summary>
    public class ChartTitle
    {
        /// <summary>
        /// 是否显示
        /// </summary>
        public Boolean show { get; set; }

        /// <summary>
        /// 主标题
        /// </summary>
        public String text { get; set; }
        /// <summary>
        /// 副标题
        /// </summary>
        public String subtext { get; set; }

        /// <summary>
        /// 标题链接
        /// </summary>
        public String link { get; set; }

        /// <summary>
        /// 打开位置
        /// </summary>
        public String target { get; set; }

        /// <summary>
        /// x轴位置(可以为left ,center, right)
        /// </summary>
        public String x { get; set; }

        /// <summary>
        /// y轴位置(可以为top, middle, bottom)
        /// </summary>
        public String y { get; set; }

        /// <summary>
        /// 构造函数
        /// </summary>
        public ChartTitle()
            : this(null, null)
        { }
        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="title">标题</param>
        public ChartTitle(String title)
            : this(title, null)
        { }
        /// <summary>
        /// /// 构造函数
        /// </summary>
        /// <param name="title">标题</param>
        /// <param name="subTitle">副标题</param>
        public ChartTitle(String title, String subTitle)
            : this(title, subTitle, LabelXLocation.Center, LabelYLocation.Bottom)
        { }
        /// <summary>
        /// /// 构造函数
        /// </summary>
        /// <param name="title">标题</param>
        /// <param name="x">x轴信息</param>
        /// <param name="y">y轴信息</param>
        public ChartTitle(String title, LabelXLocation x, LabelYLocation y)
            : this(title, null, x, y)
        { }
        /// <summary>
        /// /// 构造函数
        /// </summary>
        /// <param name="title">标题</param>
        /// <param name="subTitle">副标题</param>
        /// <param name="x">x轴信息</param>
        /// <param name="y">y轴信息</param>
        public ChartTitle(String title, String subTitle, LabelXLocation x, LabelYLocation y)
        {
            show = true;
            text = title;
            subtext = subTitle;
            this.x = x.ToString().ToLower();
            this.y = y.ToString().ToLower();
        }
    }

    /// <summary>
    /// 鼠标悬浮提示
    /// </summary>
    public class ChartToolTip
    {
        /// <summary>
        /// 是否显示
        /// </summary>
        public Boolean show { get; set; }
        /// <summary>
        /// 格式化器
        /// </summary>
        public String formatter { get; set; }

        /// <summary>
        /// 触发器
        /// </summary>
        public String trigger { get; set; }

        /// <summary>
        /// 坐标轴指示器(默认为直线)
        /// </summary>
        public AxisPointer axisPointer { get; set; }


        /// <summary>
        /// 构造函数
        /// </summary>
        public ChartToolTip()
            : this(null)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="formatter">格式化器</param>
        public ChartToolTip(string formatter)
            : this(formatter, TriggerType.Item)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="trigger">触发类型</param>
        public ChartToolTip(TriggerType trigger)
            : this(null, trigger)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="formatter">格式化器</param>
        /// <param name="trigger">触发器</param>
        public ChartToolTip(string formatter, TriggerType trigger)
        {
            show = true;
            this.formatter = formatter;
            axisPointer = new AxisPointer();
            this.trigger = trigger.ToString().ToLower();
        }
    }

    /// <summary>
    /// 坐标轴指示器
    /// </summary>
    public class AxisPointer
    {
        /// <summary>
        /// 类型(line,shadow)
        /// </summary>
        public String type { get; set; }

        /// <summary>
        /// 构造函数
        /// </summary>
        public AxisPointer()
            : this(LineType.Line)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="type">类型</param>
        public AxisPointer(LineType type)
        {
            this.type = type.ToString().ToLower();
        }
    }

    /// <summary>
    /// 标题
    /// </summary>
    public class Legend
    {
        /// <summary>
        /// 是否显示
        /// </summary>
        public Boolean show { get; set; }

        /// <summary>
        /// 源
        /// </summary>
        public String orient { get; set; }

        /// <summary>
        /// 左
        /// </summary>
        public String left { get; set; }
        /// <summary>
        /// 标题数据
        /// </summary>
        public List<LegendData> data { get; set; }

        /// <summary>
        /// 构造函数
        /// </summary>
        public Legend()
            : this(new List<LegendData>())
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="data">标题数据</param>
        public Legend(List<LegendData> data)
        {
            show = true;
            this.data = data;
            orient = OrientType.Horizontal.ToString().ToLower();
            left = LabelXLocation.Center.ToString().ToLower();
        }
    }

    /// <summary>
    /// 标题数据
    /// </summary>
    public class LegendData
    {
        /// <summary>
        /// 名称
        /// </summary>
        public String name { get; set; }

        /// <summary>
        /// 图标
        /// </summary>
        public String icon { get; set; }

        /// <summary>
        /// 样式
        /// </summary>
        public object textStyle { get; set; }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">名称</param>
        public LegendData(String name)
        {
            this.name = name;
        }
    }

    /// <summary>
    /// 图标系列
    /// </summary>
    public class ChartSeriesBase
    {
        /// <summary>
        /// 名称
        /// </summary>
        public String name { get; set; }
        /// <summary>
        /// 数据
        /// </summary>
        public List<ChartSeriesDataBase> data { get; set; }
        /// <summary>
        /// 类型(line, Gauge)
        /// </summary>
        public String type { get; set; }

        /// <summary>
        /// 构造函数
        /// </summary>
        public ChartSeriesBase()
            : this(null)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">名称</param>
        public ChartSeriesBase(String name)
        {
            this.name = name;
        }
    }

    /// <summary>
    /// 系列列表数据
    /// </summary>
    /// <typeparam name="T">类型</typeparam>
    public class ChartSeriesDataBase
    {
        /// <summary>
        /// 名称
        /// </summary>
        public String name { get; set; }
        /// <summary>
        /// 值
        /// </summary>
        public object value { get; set; }

        /// <summary>
        /// 指针,标识颜色
        /// </summary>
        public ItemStyle itemStyle { get; set; }

        /// <summary>
        /// 构造函数
        /// </summary>
        public ChartSeriesDataBase()
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">名称</param>
        public ChartSeriesDataBase(String name)
        {
            this.name = name;
        }
    }

    /// <summary>
    /// x轴
    /// </summary>
    public class XAxis
    {
        /// <summary>
        /// 标题
        /// </summary>
        public String name { get; set; }

        /// <summary>
        /// 类型
        /// </summary>
        public String type { get; set; }

        /// <summary>
        /// 坐标轴两边留白策略
        /// </summary>
        public Boolean boundaryGap { get; set; }

        /// <summary>
        /// 数据
        /// </summary>
        public List<String> data { get; set; }

        /// <summary>
        /// 轴标信息
        /// </summary>
        public AxisLabel axisLabel { get; set; }

        /// <summary>
        /// 刻度
        /// </summary>
        public AxisTick axisTick { get; set; }

        /// <summary>
        /// 标题间隔
        /// </summary>
        public int nameGap { get; set; }

        /// <summary>
        /// 标题旋转
        /// </summary>
        public int nameRotate { get; set; }

        /// <summary>
        /// 标题位置
        /// </summary>
        public string nameLocation { get; set; }

        /// <summary>
        /// 构造函数
        /// </summary>
        public XAxis()
            : this(null)
        { }
        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">名称</param>
        public XAxis(String name)
            : this(name, AxisValueType.Value)
        { }
        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">名称</param>
        /// <param name="data">值</param>
        public XAxis(String name, List<String> data)
            : this(name, AxisValueType.Category, data)
        { }
        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">名称</param>
        /// <param name="type">取值类型</param>
        public XAxis(string name, AxisValueType type)
            : this(name, type, new List<string>())
        { }
        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">名称</param>
        /// <param name="type">取值类型</param>
        /// <param name="data">值</param>
        public XAxis(string name, AxisValueType type, List<String> data)
        {
            this.name = name;
            this.type = type.ToString().ToLower();
            this.data = data;
            boundaryGap = false;
            axisLabel = new AxisLabel();
            nameLocation = "end";
            nameGap = 5;
        }
    }

    /// <summary>
    /// y轴
    /// </summary>
    public class YAxis
    {
        /// <summary>
        /// y轴标题
        /// </summary>
        public String name { get; set; }

        /// <summary>
        /// 类型
        /// </summary>
        public String type { get; set; }

        /// <summary>
        /// 轴信息
        /// </summary>
        public AxisLabel axisLabel { get; set; }

        /// <summary>
        /// 分段数(如分5段)
        /// </summary>
        public float splitNumber { get; set; }

        /// <summary>
        /// 每段间隔(如每段20度)
        /// </summary>
        public float interval { get; set; }

        /// <summary>
        /// 总值(如20 * 5 = 100度)
        /// </summary>
        public float max { get; set; }
        /// <summary>
        /// 数据
        /// </summary>
        public List<String> data { get; set; }

        /// <summary>
        /// 构造函数
        /// </summary>
        public YAxis()
            : this(null)
        { }
        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">名称</param>
        public YAxis(String name)
            : this(name, AxisValueType.Value)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">名称</param>
        /// <param name="type">取值类型</param>
        public YAxis(string name, AxisValueType type)
            : this(name, type, 10, 10, 100)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">名称</param>
        /// <param name="splitNumber">分隔数</param>
        /// <param name="interval">间隔</param>
        /// <param name="max">最大值</param>
        public YAxis(string name, int splitNumber, int interval, int max)
            : this(name, AxisValueType.Value, splitNumber, interval, max)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">名称</param>
        /// <param name="type">取值类型</param>
        /// <param name="splitNumber">分隔数</param>
        /// <param name="interval">间隔</param>
        /// <param name="max">最大值</param>
        public YAxis(string name, AxisValueType type, int splitNumber, int interval, int max)
        {
            this.name = name;
            this.type = type.ToString().ToLower();
            axisLabel = new AxisLabel();
            this.splitNumber = splitNumber;
            this.interval = interval;
            this.max = max;
        }
    }

    /// <summary>
    /// 轴标信息
    /// </summary>
    public class AxisLabel
    {
        /// <summary>
        /// 格式化器
        /// </summary>
        public String formatter { get; set; }

        /// <summary>
        /// 旋转角度
        /// </summary>
        public int rotate { get; set; }

        /// <summary>
        /// 每个刻度间隔
        /// </summary>
        public int interval { get; set; }

        /// <summary>
        /// 构造函数
        /// </summary>
        public AxisLabel()
            : this(null)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="formatter">格式化器</param>
        public AxisLabel(String formatter)
        {
            this.formatter = formatter;
            interval = 0;
            rotate = 0;
        }
    }

    /// <summary>
    /// 轴刻度线
    /// </summary>
    public class AxisTick
    {
        /// <summary>
        /// 刻度线和标签对齐
        /// </summary>
        public Boolean alignWithLabel { get; set; }

        /// <summary>
        /// 构造函数
        /// </summary>
        public AxisTick()
            : this(false)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="isAlignWithLabel">是否对齐</param>
        public AxisTick(Boolean isAlignWithLabel)
        {
            alignWithLabel = isAlignWithLabel;
        }
    }

    /// <summary>
    /// 指针,标识颜色
    /// </summary>
    public class ItemStyle
    {
        /// <summary>
        /// 常规
        /// </summary>
        public ItemStyleNormal normal { get; set; }
        public Emphasis emphasis { get; set; }
    }

    /// <summary>
    /// 常规
    /// </summary>
    public class ItemStyleNormal
    {
        /// <summary>
        /// 颜色
        /// </summary>
        public String color { get; set; }
    }

    /// <summary>
    /// 悬浮效果
    /// </summary>
    public class Emphasis
    {
        /// <summary>
        /// 图形阴影的模糊大小
        /// </summary>
        public int shadowBlur { get; set; }

        /// <summary>
        /// 阴影水平方向上的偏移距离。
        /// </summary>
        public int shadowOffsetX { get; set; }

        /// <summary>
        /// 阴影颜色
        /// </summary>
        public String shadowColor { get; set; }
    }
}
