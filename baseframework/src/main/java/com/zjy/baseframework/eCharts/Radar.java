using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ToolSiteMVC.BLL.Common
{
    /// <summary>
    /// 雷达图
    /// </summary>
    public class Radar : EChartsBase
    {
        /// <summary>
        /// 雷达维度
        /// </summary>
        public RadarDimension radar { get; set; }

        /// <summary>
        /// 构造函数
        /// </summary>
        public Radar()
            : this(null)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="title">标题</param>
        public Radar(ChartTitle title)
            : this(title, new ChartToolTip())
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="title">标题</param>
        /// <param name="toolTip">鼠标提示</param>
        public Radar(ChartTitle title, ChartToolTip toolTip)
            : this(title, toolTip, new List<ChartSeriesBase>())
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="title">标题</param>
        /// <param name="tooltip">鼠标hover提示</param>
        /// <param name="series">数据</param>
        public Radar(ChartTitle title, ChartToolTip tooltip, List<ChartSeriesBase> series)
            : base(title, series, tooltip)
        {
            radar = new RadarDimension();
            legend = new Legend();
            grid = new Grid();
        }
    }

    /// <summary>
    /// 雷达维度
    /// </summary>
    public class RadarDimension
    {
        /// <summary>
        /// 维度信息
        /// </summary>
        public List<IndicatorData> indicator { get; set; }

        /// <summary>
        /// 构造函数
        /// </summary>
        public RadarDimension()
        {
            indicator = new List<IndicatorData>();
        }
    }

    /// <summary>
    /// 雷达维度数据
    /// </summary>
    public class IndicatorData
    {
        /// <summary>
        /// 名称
        /// </summary>
        public String name { get; set; }

        /// <summary>
        /// 最大值
        /// </summary>
        public float max { get; set; }

        /// <summary>
        /// 构造函数
        /// </summary>
        public IndicatorData()
            : this(null)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">名称</param>
        public IndicatorData(string name)
            : this(name, 100)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">名称</param>
        /// <param name="max">最大值</param>
        public IndicatorData(string name, float max)
        {
            this.name = name;
            this.max = max;
        }
    }

    /// <summary>
    /// 雷达数据
    /// </summary>
    public class RadarSeries : ChartSeriesBase
    {
        /// <summary>
        /// 构造函数
        /// </summary>
        public RadarSeries()
            : this(null)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">名称</param>
        public RadarSeries(string name)
            : this(name, null)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">名称</param>
        /// <param name="data">数据</param>
        public RadarSeries(string name, List<RadarSeriesData> data)
        {
            base.name = name;
            base.data = data.Select(item => (ChartSeriesDataBase)item).ToList();
            type = ChartType.Radar.ToString().ToLower();
        }
    }

    /// <summary>
    /// 仪表盘数据
    /// </summary>
    public class RadarSeriesData : ChartSeriesDataBase
    {
        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="value">值</param>
        public RadarSeriesData(List<float> value)
            : this(value, null)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="value">值</param>
        /// <param name="name">名称</param>
        public RadarSeriesData(List<float> value, String name)
        {
            this.name = name;
            this.value = value;
        }
    }
}
