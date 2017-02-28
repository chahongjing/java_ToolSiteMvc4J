package com.zjy.baseframework.eCharts.common;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

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
        public static List<String> Colors = new ArrayList<>();

        static {
            Colors.add("#c23531");
            Colors.add("#2f4554");
            Colors.add("#d48265");
            Colors.add("#91c7ae");
            Colors.add("#749f83");

            Colors.add("#ca8622");
            Colors.add("#bda29a");
            Colors.add("#6e7074");
            Colors.add("#546570");
            Colors.add("#c4ccd3");
            Colors.add("#3398DB");
            Colors.add("#61a0a8");
        }
        /// <summary>
        /// 图表标题
        /// </summary>
        public ChartTitle title;

        /// <summary>
        /// 鼠标悬浮提示
        /// </summary>
        public ChartToolTip tooltip;

        /// <summary>
        /// 标题
        /// </summary>
        public Legend legend;

        /// <summary>
        /// 网格
        /// </summary>
        public Grid grid;

        /// <summary>
        /// 数据
        /// </summary>
        public List<ChartSeriesBase> series;

        /// <summary>
        /// x轴
        /// </summary>
        public List<XAxis> xAxis;

        /// <summary>
        /// y轴
        /// </summary>
        public List<YAxis> yAxis;

        /// <summary>
        /// 构造函数
        /// </summary>
        public EChartsBase()
        { this(null);}
        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="title">标题</param>
        public EChartsBase(ChartTitle title)
        { this(title, new ArrayList<>(), new ChartToolTip());}
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
        public String ToString()
    {
        return JSON.toJSONString(this);
    }
}
