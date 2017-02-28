using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ToolSiteMVC.BLL.Common
{
    /// <summary>
    /// 仪表盘
    /// </summary>
    public class DashBoard : EChartsBase
    {
        /// <summary>
        /// 构造函数
        /// </summary>
        public DashBoard()
            : this(null)
        { }
        
        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="title">标题</param>
        public DashBoard(ChartTitle title)
            : this(title, new List<ChartSeriesBase>(), new ChartToolTip())
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="title">标题</param>
        /// <param name="series">数据</param>
        /// <param name="tooltip">鼠标hover提示</param>
        public DashBoard(ChartTitle title, List<ChartSeriesBase> series, ChartToolTip tooltip)
            : base(title, series, tooltip)
        { }

        /// <summary>
        /// 重写tostring, 设置最后一个指针的颜色
        /// </summary>
        /// <returns></returns>
        public override string ToString()
        {
            //if (this.series.Any())
            //{
            //    var it = this.series.Last() as DashBoardSeries;
            //    it.axisLine.lineStyle.color.ForEach(innerItem => innerItem[1] = Colors.Last());
            //}
            return base.ToString();
        }
    }

    /// <summary>
    /// 数据
    /// </summary>
    public class DashBoardSeries : ChartSeriesBase
    {
        /// <summary>
        /// 详情
        /// </summary>
        public DashBoardSeriesDetail detail { get; set; }

        /// <summary>
        /// 轴线
        /// </summary>
        //public DashBoardSeriesAxisLine axisLine { get; set; }

        /// <summary>
        /// 轴标
        /// </summary>
        public AxisLabel axisLabel { get; set; }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">名称</param>
        public DashBoardSeries(String name)
            : this(name, null)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">名称</param>
        /// <param name="data">数据</param>
        public DashBoardSeries(String name, List<DashBoardSeriesData> data)
        {
            type = ChartType.Gauge.ToString().ToLower();
            //axisLine = new DashBoardSeriesAxisLine();
            axisLabel = new AxisLabel();
            base.data = data.Select(item => (ChartSeriesDataBase)item).ToList();
            this.name = name;
        }
    }
    /// <summary>
    /// 仪表盘数据
    /// </summary>
    public class DashBoardSeriesData : ChartSeriesDataBase
    {
        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="value">值</param>
        public DashBoardSeriesData(String value)
            : this(value, null)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="value">值</param>
        /// <param name="name">名称</param>
        public DashBoardSeriesData(String value, String name)
        {
            this.name = name;
            this.value = value;
        }
    }

    /// <summary>
    /// 详细信息
    /// </summary>
    public class DashBoardSeriesDetail
    {
        /// <summary>
        /// 格式化器
        /// </summary>
        public String formatter { get; set; }
        /// <summary>
        /// 是否显示
        /// </summary>
        public Boolean show { get; set; }
    }

    /// <summary>
    /// 仪表盘指针
    /// </summary>
    public class DashBoardSeriesAxisLine
    {
        /// <summary>
        /// 仪表盘指针样式
        /// </summary>
        public DashBoardSeriesAxisLineLineStyle lineStyle { get; set; }

        /// <summary>
        /// 构造函数
        /// </summary>
        public DashBoardSeriesAxisLine()
        {
            lineStyle = new DashBoardSeriesAxisLineLineStyle();
        }
    }

    /// <summary>
    /// 仪表盘指针样式
    /// </summary>
    public class DashBoardSeriesAxisLineLineStyle
    {
        /// <summary>
        /// 每个阶段颜色(比如0-30一个颜色, 30-70一个颜色, 70-100一个颜色)
        /// </summary>
        public List<List<object>> color { get; set; }

        /// <summary>
        /// 构造函数
        /// </summary>
        public DashBoardSeriesAxisLineLineStyle()
        {
            //color = new List<List<object>>();
        }
    }
}
