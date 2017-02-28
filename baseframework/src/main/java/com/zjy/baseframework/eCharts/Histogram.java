using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ToolSiteMVC.BLL.Common
{
    /// <summary>
    /// 柱状图
    /// </summary>
    public class Histogram : EChartsBase
    {
        /// <summary>
        /// 颜色
        /// </summary>
        public List<String> color { get; set; }

        /// <summary>
        /// 构造函数
        /// </summary>
        public Histogram()
            : this(null)
        { }
        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="title">标题</param>
        public Histogram(ChartTitle title)
            : this(title, new List<ChartSeriesBase>(), new ChartToolTip())
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="title">标题</param>
        /// <param name="series">数据</param>
        /// <param name="tooltip">鼠标hover提示</param>
        public Histogram(ChartTitle title, List<ChartSeriesBase> series, ChartToolTip tooltip)
            : base(title, series, tooltip)
        {
            color = EChartsBase.Colors;
            grid = new Grid();
        }
    }

    /// <summary>
    /// 网格
    /// </summary>
    public class Grid
    {
        /// <summary>
        /// 左边距
        /// </summary>
        public String left { get; set; }

        /// <summary>
        /// 右边距
        /// </summary>
        public String right { get; set; }
        /// <summary>
        /// 上边距
        /// </summary>
        public String top { get; set; }
        /// <summary>
        /// 下边距
        /// </summary>
        public String bottom { get; set; }

        /// <summary>
        /// 是否包含标签
        /// </summary>
        public Boolean containLabel { get; set; }

        /// <summary>
        /// 构造函数
        /// </summary>
        public Grid()
        {
            containLabel = true;
            left = "3%";
            right = "15%";
            top = "10%";
            bottom = "10%";
        }
    }

    /// <summary>
    /// 柱状图数据
    /// </summary>
    public class HistogramSeries : ChartSeriesBase
    {
        /// <summary>
        /// 宽度
        /// </summary>
        public String barWidth { get; set; }

        /// <summary>
        /// 构造函数
        /// </summary>
        public HistogramSeries()
            : this(null)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">名称</param>
        public HistogramSeries(string name)
            : this(name, null)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">名称</param>
        /// <param name="data">值</param>
        public HistogramSeries(string name, List<HistogramSeriesData> data)
        {
            base.name = name;
            base.data = data.Select(item => (ChartSeriesDataBase)item).ToList();
            type = ChartType.Bar.ToString().ToLower();
        }
    }

    public class HistogramSeriesData : ChartSeriesDataBase
    {
        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="value">值</param>
        public HistogramSeriesData(String value)
            : this(value, null)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="value">值</param>
        /// <param name="name">名称</param>
        public HistogramSeriesData(String value, String name)
        {
            this.name = name;
            this.value = value;
        }
    }
}
