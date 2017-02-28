using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ToolSiteMVC.BLL.Common
{
    /// <summary>
    /// 折线表
    /// </summary>
    public class LineChart : EChartsBase
    {
        /// <summary>
        /// 构造函数
        /// </summary>
        public LineChart()
            : this(null)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="title">标题</param>
        public LineChart(ChartTitle title)
            : this(title, new List<ChartSeriesBase>(), new ChartToolTip())
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="title">标题</param>
        /// <param name="series">数据</param>
        /// <param name="tooltip">鼠标hover提示</param>
        public LineChart(ChartTitle title, List<ChartSeriesBase> series, ChartToolTip tooltip)
            : base(title, series, tooltip)
        {
            grid = new Grid();
        }
    }

    /// <summary>
    /// 折线数据
    /// </summary>
    public class LineChartSeries : ChartSeriesBase
    {

        /// <summary>
        /// 构造函数
        /// </summary>
        public LineChartSeries()
            : this(null)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">名称</param>
        public LineChartSeries(string name)
            : this(name, null)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">拆线名称</param>
        /// <param name="data">值</param>
        public LineChartSeries(string name, List<LineChartSeriesData> data)
        {
            base.name = name;
            base.data = data.Select(item => (ChartSeriesDataBase)item).ToList();
            type = ChartType.Line.ToString().ToLower();
        }

    }
    /// <summary>
    /// 折线数据
    /// </summary>
    public class LineChartSeriesData : ChartSeriesDataBase
    { 
        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="value">值</param>
        public LineChartSeriesData(String value)
            : this(value, null)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="value">值</param>
        /// <param name="name">名称</param>
        public LineChartSeriesData(String value, String name)
        {
            this.name = name;
            this.value = value;
        }
    }
}
