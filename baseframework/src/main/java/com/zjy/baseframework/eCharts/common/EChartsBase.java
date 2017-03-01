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
        private ChartTitle title;

        /// <summary>
        /// 鼠标悬浮提示
        /// </summary>
        private ChartToolTip tooltip;

        /// <summary>
        /// 标题
        /// </summary>
        private Legend legend;

        /// <summary>
        /// 网格
        /// </summary>
        private Grid grid;

        /// <summary>
        /// 数据
        /// </summary>
        private List<ChartSeriesBase> series;

        /// <summary>
        /// x轴
        /// </summary>
        private List<XAxis> xAxis;

        /// <summary>
        /// y轴
        /// </summary>
        private List<YAxis> yAxis;

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

        public static List<String> getColors() {
            return Colors;
        }

        public static void setColors(List<String> colors) {
            Colors = colors;
        }

        public ChartTitle getTitle() {
            return title;
        }

        public void setTitle(ChartTitle title) {
            this.title = title;
        }

        public ChartToolTip getTooltip() {
            return tooltip;
        }

        public void setTooltip(ChartToolTip tooltip) {
            this.tooltip = tooltip;
        }

        public Legend getLegend() {
            return legend;
        }

        public void setLegend(Legend legend) {
            this.legend = legend;
        }

        public Grid getGrid() {
            return grid;
        }

        public void setGrid(Grid grid) {
            this.grid = grid;
        }

        public List<ChartSeriesBase> getSeries() {
            return series;
        }

        public void setSeries(List<ChartSeriesBase> series) {
            this.series = series;
        }

        public List<XAxis> getxAxis() {
            return xAxis;
        }

        public void setxAxis(List<XAxis> xAxis) {
            this.xAxis = xAxis;
        }

        public List<YAxis> getyAxis() {
            return yAxis;
        }

        public void setyAxis(List<YAxis> yAxis) {
            this.yAxis = yAxis;
        }
    }
