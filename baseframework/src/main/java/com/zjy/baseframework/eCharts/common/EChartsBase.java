package com.zjy.baseframework.eCharts.common;

/**
 * Created by Administrator on 2017/2/28.
 */
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
