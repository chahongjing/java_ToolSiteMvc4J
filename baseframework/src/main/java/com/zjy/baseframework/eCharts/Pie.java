using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ToolSiteMVC.BLL.Common
{
    /// <summary>
    /// 饼图
    /// </summary>
    public class Pie : EChartsBase
    {
        /// <summary>
        /// 构造函数
        /// </summary>
        public Pie()
            : this(null)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="title">标题</param>
        public Pie(ChartTitle title)
            : this(title, new List<ChartSeriesBase>(), new ChartToolTip())
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="title">标题</param>
        /// <param name="series">数据</param>
        /// <param name="tooltip">鼠标hover提示</param>
        public Pie(ChartTitle title, List<ChartSeriesBase> series, ChartToolTip tooltip)
            : base(title, series, tooltip)
        { }
    }

    /// <summary>
    /// 饼图系列
    /// </summary>
    public class PieSeries : ChartSeriesBase
    {
        /// <summary>
        /// 饼图半径
        /// </summary>
        public String radius { get; set; }

        /// <summary>
        /// 饼图的中心（圆心）坐标
        /// </summary>
        public List<String> center { get; set; }

        /// <summary>
        /// 悬浮状态
        /// </summary>
        public ItemStyle itemStyle { get; set; }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">名称</param>
        public PieSeries(String name)
            : this(name, null)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="name">名称</param>
        /// <param name="data">数据</param>
        public PieSeries(String name, List<PieSeriesData> data)
        {
            type = ChartType.Pie.ToString().ToLower();
            base.data = data.Select(item => (ChartSeriesDataBase)item).ToList();
            this.name = name;
        }
    }

    /// <summary>
    /// 仪表盘数据
    /// </summary>
    public class PieSeriesData : ChartSeriesDataBase
    {
        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="value">值</param>
        public PieSeriesData(float value)
            : this(value, null)
        { }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="value">值</param>
        /// <param name="name">名称</param>
        public PieSeriesData(float value, String name)
        {
            this.name = name;
            this.value = value;
        }
    }
}
