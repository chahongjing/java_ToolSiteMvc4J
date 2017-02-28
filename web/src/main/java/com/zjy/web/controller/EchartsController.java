package com.zjy.web.controller;

import com.alibaba.fastjson.JSON;
import com.zjy.baseframework.PartialViewHelper;
import com.zjy.baseframework.eCharts.common.*;
import com.zjy.baseframework.eCharts.dashBoard.DashBoard;
import com.zjy.baseframework.eCharts.dashBoard.DashBoardSeries;
import com.zjy.baseframework.eCharts.dashBoard.DashBoardSeriesData;
import com.zjy.baseframework.eCharts.dashBoard.DashBoardSeriesDetail;
import com.zjy.baseframework.eCharts.enums.*;
import com.zjy.baseframework.eCharts.histogram.Histogram;
import com.zjy.baseframework.eCharts.histogram.HistogramSeries;
import com.zjy.baseframework.eCharts.histogram.HistogramSeriesData;
import com.zjy.baseframework.eCharts.lineChart.LineChart;
import com.zjy.baseframework.eCharts.lineChart.LineChartSeries;
import com.zjy.baseframework.eCharts.lineChart.LineChartSeriesData;
import com.zjy.baseframework.eCharts.pie.Pie;
import com.zjy.baseframework.eCharts.pie.PieSeries;
import com.zjy.baseframework.eCharts.pie.PieSeriesData;
import com.zjy.baseframework.eCharts.radar.IndicatorData;
import com.zjy.baseframework.eCharts.radar.Radar;
import com.zjy.baseframework.eCharts.radar.RadarSeries;
import com.zjy.baseframework.eCharts.radar.RadarSeriesData;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by chahongjing on 2017/2/28.
 */
@Controller
@RequestMapping("/echarts")
public class EchartsController {

    @RequestMapping("/dashboard.do")
    public ModelAndView dashBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("echarts");

        Map<String, Float> list = new HashMap<>();
        list.put("一季度", 0.3f);
        list.put("二季度", 0.8f);
        list.put("三季度", 0.5f);
        list.put("四季度", 0.95f);

        //region 仪表盘
        // 仪表盘, 标题
        DashBoard db = new DashBoard(new ChartTitle("学习仪表盘", "学生的排名情况"));
        // 鼠标hover提示
        db.tooltip = new ChartToolTip("{b}<br/>排名 : {c}%");
        // 仪表盘数据, x轴名称, 仪表盘名称, 值
        List<DashBoardSeriesData> ll = new ArrayList<>();
        for (Map.Entry<String, Float> entry : list.entrySet()) {
            DashBoardSeriesData d = new DashBoardSeriesData(String.valueOf(Math.round(entry.getValue() * 100)), entry.getKey());
            d.itemStyle = new ItemStyle();
            d.itemStyle.normal = new ItemStyleNormal();
            d.itemStyle.normal.color = DashBoard.Colors.get(ll.size());
            ll.add(d);
        }

        DashBoardSeries dbs = new DashBoardSeries("学习仪表盘", ll);
        // 显示值, 是否显示, 值格式化器
        dbs.detail = new DashBoardSeriesDetail();
        dbs.detail.show = false;
        dbs.detail.formatter = "{value}%";
        // 刻度格式化器
        dbs.axisLabel = new AxisLabel("{value}%");
        db.series.add(dbs);
        mv.addObject("dashBoard", JSON.toJSONString(db));
        //endregion

        //region 折线图
        // 折线图, 标题
        LineChart lc = new LineChart(new ChartTitle("成长轨迹图", "学生的排名情况"));
        List<LegendData> legendL = new ArrayList<>();
        legendL.add(new LegendData("学习轨迹图"));
        legendL.add(new LegendData("成长轨迹图"));
        lc.legend = new Legend(legendL);
        // 鼠标hover提示
        lc.tooltip = new ChartToolTip("{b}<br/>{a0}排名 : {c0}%<br/>{a1}排名 : {c1}%", TriggerType.Axis);
        // x轴相关信息
        lc.xAxis = new ArrayList<>();
        lc.xAxis.add(new XAxis("考试编号", new ArrayList<>(list.keySet())));
        lc.xAxis.get(0).axisLabel = new AxisLabel();
        lc.xAxis.get(0).axisLabel.rotate = 8;
        // y轴相关信息
        lc.yAxis = new ArrayList<>();
        lc.yAxis.add(new YAxis("排名百分比", 10, 10, 100));
        lc.yAxis.get(0).axisLabel = new AxisLabel("{value}%");

        List<LineChartSeriesData> li1 = new ArrayList<>();
        List<LineChartSeriesData> li2 = new ArrayList<>();
        for (Map.Entry<String, Float> entry : list.entrySet()) {
            LineChartSeriesData lcsd = new LineChartSeriesData(String.valueOf(Math.round(entry.getValue() * 100)));
            li1.add(lcsd);
            LineChartSeriesData lcsd2 = new LineChartSeriesData(String.valueOf(Math.round(entry.getValue() * 100)));
            li2.add(lcsd2);
        }
        // 折线图的值
        lc.series.add(new LineChartSeries("学习轨迹图", li1));
        Collections.reverse(li2);
        // 折线图的值
        lc.series.add(new LineChartSeries("成长轨迹图", li2));
        mv.addObject("lineChart", JSON.toJSONString(lc));
        //endregion

        //region 柱状图
        // 柱状图, 标题
        Histogram his = new Histogram(new ChartTitle("学习轨迹图", "学生的排名情况"));
        List<LegendData> legendH = new ArrayList<>();
        legendH.add(new LegendData("学习轨迹图"));
        legendH.add(new LegendData("成长轨迹图"));
        his.legend = new Legend(legendH);
        // 鼠标hover提示
        his.tooltip = new ChartToolTip("{b} <br/>{a0}排名 : {c0}%<br/>{a1}排名 : {c1}%", TriggerType.Axis);
        his.tooltip.axisPointer = new AxisPointer(LineType.Shadow);
        // x轴相关信息, 数据值
        his.xAxis = new ArrayList<>();
        his.xAxis.add(new XAxis("考试编号", AxisValueType.Category, new ArrayList<>(list.keySet())));
        his.xAxis.get(0).boundaryGap = true;
        his.xAxis.get(0).axisTick = new AxisTick(true);
        // y轴相关信息
        his.yAxis = new ArrayList<>();
        his.yAxis.add(new YAxis("排名百分比", AxisValueType.Value, 10, 10, 100));
        his.yAxis.get(0).axisLabel = new AxisLabel("{value}%");

        List<HistogramSeriesData> hsd1 = new ArrayList<>();
        List<HistogramSeriesData> hsd2 = new ArrayList<>();
        for (Map.Entry<String, Float> entry : list.entrySet()) {
            HistogramSeriesData lcsd = new HistogramSeriesData(String.valueOf(Math.round(entry.getValue() * 100)));
            hsd1.add(lcsd);
            HistogramSeriesData lcsd2 = new HistogramSeriesData(String.valueOf(Math.round(entry.getValue() * 100)));
            hsd2.add(lcsd2);
        }
        // 柱状图的值
        his.series.add(new HistogramSeries("学习轨迹图", hsd1));
        Collections.reverse(li2);
        // 柱状图的值
        his.series.add(new HistogramSeries("成长轨迹图", hsd2));

        mv.addObject("histogram", JSON.toJSONString(his));
        //endregion

        //region 雷达图
        // 雷达图, 标题, 副标题, 鼠标提示为item
        Radar radar = new Radar(new ChartTitle("学习轨迹图", "学生的排名情况"));
        List<LegendData> legend = new ArrayList<>();
        legend.add(new LegendData("预算分配"));
        ;
        legend.add(new LegendData("实际开销"));
        // 每项标题
        radar.legend = new Legend(legend);
        radar.legend.orient = OrientType.Vertical.toString().toLowerCase();
        radar.legend.left = LabelXLocation.Left.toString().toLowerCase();

        // 维度
        radar.radar.indicator.add(new IndicatorData("销售"));
        radar.radar.indicator.add(new IndicatorData("管理"));
        radar.radar.indicator.add(new IndicatorData("信息技术"));
        radar.radar.indicator.add(new IndicatorData("客服"));
        radar.radar.indicator.add(new IndicatorData("研发"));
        radar.radar.indicator.add(new IndicatorData("市场"));

        // 数据
        List<RadarSeriesData> radarData = new ArrayList<>();
        Random random = new Random(1000);
        legend.forEach(item -> {
            List<Float> l = new ArrayList<>();
            IntStream.range(1, 7).forEach(innerItem -> l.add(random.nextFloat() * 100));
            RadarSeriesData rsd = new RadarSeriesData(l, item.name);
            radarData.add(rsd);
        });

        radar.series.add(new RadarSeries("学习轨迹图", radarData));
        mv.addObject("radar", JSON.toJSONString(radar));
        //endregion

        //region 饼图
        // 饼图, 标题, 副标题, 鼠标提示为item
        Pie pie = new Pie(new ChartTitle("学习轨迹图", "学生的排名情况"));
        // 鼠标hover提示
        pie.tooltip = new ChartToolTip("{a} <br/>{b} : {c} ({d}%)");
        // 每项标题
        List<LegendData> legendP = new ArrayList<>();
        legendP.add(new LegendData("直接访问"));
        legendP.add(new LegendData("邮件营销"));
        legendP.add(new LegendData("联盟广告"));
        legendP.add(new LegendData("视频广告"));
        legendP.add(new LegendData("搜索引擎"));

        pie.legend = new Legend(legendP);
        pie.legend.orient = OrientType.Vertical.toString().toLowerCase();
        pie.legend.left = LabelXLocation.Left.toString().toLowerCase();

        // 数据
        List<PieSeriesData> pieData = new ArrayList<>();
        legendP.forEach(item ->
        {
            PieSeriesData rsd = new PieSeriesData(random.nextFloat() * 100, item.name);
            pieData.add(rsd);
        });
        PieSeries series = new PieSeries("学习轨迹图", pieData);
        series.radius = "70%";
        series.center = new ArrayList<>();
        series.center.add("50%");
        series.center.add("45%");
        series.itemStyle = new ItemStyle();
        series.itemStyle.emphasis = new Emphasis();
        series.itemStyle.emphasis.shadowBlur = 10;
        series.itemStyle.emphasis.shadowColor = "rgba(0, 0, 0, 0.5)";
        series.itemStyle.emphasis.shadowOffsetX = 0;
        pie.series.add(series);
        mv.addObject("pie", JSON.toJSONString(pie));
        //endregion

        return mv;
    }
}
