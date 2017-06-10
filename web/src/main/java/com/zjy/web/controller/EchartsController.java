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

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        db.setTooltip(new ChartToolTip("{b}<br/>排名 : {c}%"));
        // 仪表盘数据, x轴名称, 仪表盘名称, 值
        List<DashBoardSeriesData> ll = new ArrayList<>();
        for (Map.Entry<String, Float> entry : list.entrySet()) {
            DashBoardSeriesData d = new DashBoardSeriesData(String.valueOf(Math.round(entry.getValue() * 100)), entry.getKey());
            d.setItemStyle(new ItemStyle());
            d.getItemStyle().setNormal(new ItemStyleNormal());
            d.getItemStyle().getNormal().setColor(DashBoard.Colors.get(ll.size()));
            ll.add(d);
        }

        DashBoardSeries dbs = new DashBoardSeries("学习仪表盘", ll);
        // 显示值, 是否显示, 值格式化器
        dbs.setDetail(new DashBoardSeriesDetail());
        dbs.getDetail().setShow(false);
        dbs.getDetail().setFormatter("{value}%");
        // 刻度格式化器
        dbs.setAxisLabel(new AxisLabel("{value}%"));
        db.getSeries().add(dbs);
        mv.addObject("dashBoard", JSON.toJSONString(db));
        //endregion

        //region 折线图
        // 折线图, 标题
        LineChart lc = new LineChart(new ChartTitle("成长轨迹图", "学生的排名情况"));
        List<LegendData> legendL = new ArrayList<>();
        legendL.add(new LegendData("学习轨迹图"));
        legendL.add(new LegendData("成长轨迹图"));
        lc.setLegend(new Legend(legendL));
        // 鼠标hover提示
        lc.setTooltip(new ChartToolTip("{b}<br/>{a0}排名 : {c0}%<br/>{a1}排名 : {c1}%", TriggerType.Axis));
        // x轴相关信息
        lc.setXAxis(new ArrayList<>());
        lc.getXAxis().add(new XAxis("考试编号", new ArrayList<>(list.keySet())));
        lc.getXAxis().get(0).setAxisLabel(new AxisLabel());
        lc.getXAxis().get(0).getAxisLabel().setRotate(8);
        // y轴相关信息
        lc.setYAxis(new ArrayList<>());
        lc.getYAxis().add(new YAxis("排名百分比", 10, 10, 100));
        lc.getYAxis().get(0).setAxisLabel(new AxisLabel("{value}%"));

        List<LineChartSeriesData> li1 = new ArrayList<>();
        List<LineChartSeriesData> li2 = new ArrayList<>();
        for (Map.Entry<String, Float> entry : list.entrySet()) {
            LineChartSeriesData lcsd = new LineChartSeriesData(String.valueOf(Math.round(entry.getValue() * 100)));
            li1.add(lcsd);
            LineChartSeriesData lcsd2 = new LineChartSeriesData(String.valueOf(Math.round(entry.getValue() * 100)));
            li2.add(lcsd2);
        }
        // 折线图的值
        lc.getSeries().add(new LineChartSeries("学习轨迹图", li1));
        Collections.reverse(li2);
        // 折线图的值
        lc.getSeries().add(new LineChartSeries("成长轨迹图", li2));
        mv.addObject("lineChart", JSON.toJSONString(lc));
        //endregion

        //region 柱状图
        // 柱状图, 标题
        Histogram his = new Histogram(new ChartTitle("学习轨迹图", "学生的排名情况"));
        List<LegendData> legendH = new ArrayList<>();
        legendH.add(new LegendData("学习轨迹图"));
        legendH.add(new LegendData("成长轨迹图"));
        his.setLegend(new Legend(legendH));
        // 鼠标hover提示
        his.setTooltip(new ChartToolTip("{b} <br/>{a0}排名 : {c0}%<br/>{a1}排名 : {c1}%", TriggerType.Axis));
        his.getTooltip().setAxisPointer(new AxisPointer(LineType.Shadow));
        // x轴相关信息, 数据值
        his.setXAxis(new ArrayList<>());
        his.getXAxis().add(new XAxis("考试编号", AxisValueType.Category, new ArrayList<>(list.keySet())));
        his.getXAxis().get(0).setBoundaryGap(true);
        his.getXAxis().get(0).setAxisTick(new AxisTick(true));
        // y轴相关信息
        his.setYAxis(new ArrayList<>());
        his.getYAxis().add(new YAxis("排名百分比", AxisValueType.Value, 10, 10, 100));
        his.getYAxis().get(0).setAxisLabel(new AxisLabel("{value}%"));

        List<HistogramSeriesData> hsd1 = new ArrayList<>();
        List<HistogramSeriesData> hsd2 = new ArrayList<>();
        for (Map.Entry<String, Float> entry : list.entrySet()) {
            HistogramSeriesData lcsd = new HistogramSeriesData(String.valueOf(Math.round(entry.getValue() * 100)));
            hsd1.add(lcsd);
            HistogramSeriesData lcsd2 = new HistogramSeriesData(String.valueOf(Math.round(entry.getValue() * 100)));
            hsd2.add(lcsd2);
        }
        // 柱状图的值
        his.getSeries().add(new HistogramSeries("学习轨迹图", hsd1));
        Collections.reverse(li2);
        // 柱状图的值
        his.getSeries().add(new HistogramSeries("成长轨迹图", hsd2));

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
        radar.setLegend(new Legend(legend));
        radar.getLegend().setOrient(OrientType.Vertical.toString().toLowerCase());
        radar.getLegend().setLeft(LabelXLocation.Left.toString().toLowerCase());

        // 维度
        radar.getRadar().getIndicator().add(new IndicatorData("销售"));
        radar.getRadar().getIndicator().add(new IndicatorData("管理"));
        radar.getRadar().getIndicator().add(new IndicatorData("信息技术"));
        radar.getRadar().getIndicator().add(new IndicatorData("客服"));
        radar.getRadar().getIndicator().add(new IndicatorData("研发"));
        radar.getRadar().getIndicator().add(new IndicatorData("市场"));

        // 数据
        List<RadarSeriesData> radarData = new ArrayList<>();
        Random random = new Random(1000);
        legend.forEach(item -> {
            List<Float> l = new ArrayList<>();
            IntStream.range(1, 7).forEach(innerItem -> l.add(random.nextFloat() * 100));
            RadarSeriesData rsd = new RadarSeriesData(l, item.getName());
            radarData.add(rsd);
        });

        radar.getSeries().add(new RadarSeries("学习轨迹图", radarData));
        mv.addObject("radar", JSON.toJSONString(radar));
        //endregion

        //region 饼图
        // 饼图, 标题, 副标题, 鼠标提示为item
        Pie pie = new Pie(new ChartTitle("学习轨迹图", "学生的排名情况"));
        // 鼠标hover提示
        pie.setTooltip(new ChartToolTip("{a} <br/>{b} : {c} ({d}%)"));
        // 每项标题
        List<LegendData> legendP = new ArrayList<>();
        legendP.add(new LegendData("直接访问"));
        legendP.add(new LegendData("邮件营销"));
        legendP.add(new LegendData("联盟广告"));
        legendP.add(new LegendData("视频广告"));
        legendP.add(new LegendData("搜索引擎"));

        pie.setLegend(new Legend(legendP));
        pie.getLegend().setOrient(OrientType.Vertical.toString().toLowerCase());
        pie.getLegend().setLeft(LabelXLocation.Left.toString().toLowerCase());

        // 数据
        List<PieSeriesData> pieData = new ArrayList<>();
        legendP.forEach(item ->
        {
            PieSeriesData rsd = new PieSeriesData(random.nextFloat() * 100, item.getName());
            pieData.add(rsd);
        });
        PieSeries series = new PieSeries("学习轨迹图", pieData);
        series.setRadius("70%");
        series.setCenter(new ArrayList<>());
        series.getCenter().add("50%");
        series.getCenter().add("45%");
        series.setItemStyle(new ItemStyle());
        series.getItemStyle().setEmphasis(new Emphasis());
        series.getItemStyle().getEmphasis().setShadowBlur(10);
        series.getItemStyle().getEmphasis().setShadowColor("rgba(0, 0, 0, 0.5)");
        series.getItemStyle().getEmphasis().setShadowOffsetX(0);
        pie.getSeries().add(series);
        mv.addObject("pie", JSON.toJSONString(pie));
        //endregion

        return mv;
    }
}
