<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
</head>
<body>
<div class="row">
    <div class="col-sm-6">
        <!-- 折线 -->
        <div id="dashBoard" style="width: 100%; height: 400px;"></div>
    </div>
    <div class="col-sm-6">
        <!-- 折线 -->
        <div id="lineChart" style="width: 100%; height: 400px;"></div>
    </div>
</div>
<div class="row">
    <div class="col-sm-6">
        <!-- 柱状图 -->
        <div id="histogram" style="width: 100%; height: 400px;"></div>
    </div>
    <div class="col-sm-6">
        <!-- 雷达图 -->
        <div id="radar" style="width: 100%; height: 400px;"></div>
    </div>
</div>
<div class="row">
    <div class="col-sm-6">
        <!-- 饼图 -->
        <div id="pie" style="width: 100%; height: 400px;"></div>
    </div>
    <div class="col-sm-6">
        <!-- 饼图 -->
        <div id="zhengtai" style="width: 100%; height: 400px;"></div>
    </div>
</div>
<jsSection>
    <script src="${ctx}/js/echarts.min.js"></script>
    <script>

        vueMounted = function (){
            // 准备数据
            var optionDashBoard = ${dashBoard};
            var optionLineChart = ${lineChart};
            var optionHistogram = ${histogram};
            var optionRadar = ${radar};
            var optionPie = ${pie};

            var optionZhengtai = {
                "color":[
                    "#6087CF"
                ],
                "title":{
                    "text":"",
                    "subtext":""
                },
                "tooltip":{
                    "trigger":"item"
                },
                "legend":{
                    "data":[

                    ]
                },
                "toolbox":{
                    "show":false,
                    "feature":{
                        "mark":{
                            "show":true
                        },
                        "dataView":{
                            "show":true,
                            "readOnly":false
                        },
                        "magicType":{
                            "show":true,
                            "type":[
                                "line",
                                "bar"
                            ]
                        },
                        "restore":{
                            "show":true
                        },
                        "saveAsImage":{
                            "show":true
                        }
                    }
                },
                "calculable":false,
                "xAxis":[
                    {
                        "type":"category",
                        "data":[
                            "[0,10]",
                            "(10,20]",
                            "(20,30]",
                            "(30,40]",
                            "(40,50]",
                            "(50,60]",
                            "(60,70]",
                            "(70,80]",
                            "(80,90]",
                            "(90,100]"
                        ],
                        "name":""
                    },
                    {
                        "name":"分数",
                        "show":false,
                        "type":"value",
                        "splitLine":{
                            "lineStyle":{
                                "type":"dashed"
                            }
                        },
                        "min":0,
                        "max":100,
                        "interval":10
                    }
                ],
                "yAxis":[
                    {
                        "type":"value",
                        "axisLabel":{
                            "formatter":"{value}"
                        },
                        "name":"人数",
                        "min":0,
                        "minInterval":1,
                        "interval":2,
                        "max":20
                    },
                    {
                        "name":"正态分布",
                        "show":true,
                        "type":"value",
                        "splitLine":{
                            "lineStyle":{
                                "type":"dashed"
                            }
                        }
                    }
                ],
                "series":[
                    {
                        "name":"区间人数",
                        "type":"bar",
                        "data":[
                            0,
                            1,
                            1,
                            0,
                            11,
                            4,
                            3,
                            1,
                            1,
                            0
                        ]
                    },
                    {
                        "name":"line",
                        "type":"line",
                        "xAxisIndex":1,
                        "yAxisIndex":1,
                        "lineStyle":{
                            "normal":{
                                "color":"#CA4E4B"
                            }
                        },
                        "showSymbol":false,
                        "smooth":true,
                        "data":[
                            [
                                20,
                                0.031598805961163566
                            ],
                            [
                                29,
                                0.10908348502912833
                            ],
                            [
                                41,
                                0.30001130522517055
                            ],
                            [
                                42,
                                0.31579663312669004
                            ],
                            [
                                44,
                                0.34460888602692685
                            ],
                            [
                                45,
                                0.3572527440577784
                            ],
                            [
                                45,
                                0.3572527440577784
                            ],
                            [
                                46,
                                0.36848324918372943
                            ],
                            [
                                47,
                                0.3781403330778938
                            ],
                            [
                                48,
                                0.38608357867285736
                            ],
                            [
                                49,
                                0.3921956146701
                            ],
                            [
                                50,
                                0.3963850003307883
                            ],
                            [
                                50,
                                0.3963850003307883
                            ],
                            [
                                51,
                                0.3985885014804987
                            ],
                            [
                                53,
                                0.39693473096110676
                            ],
                            [
                                56,
                                0.37971579948649725
                            ],
                            [
                                60,
                                0.3333351771416598
                            ],
                            [
                                63,
                                0.2865994148782126
                            ],
                            [
                                67,
                                0.2182246931301749
                            ],
                            [
                                69,
                                0.1847041443497608
                            ],
                            [
                                77,
                                0.07735540294824718
                            ],
                            [
                                83,
                                0.0325319675819848
                            ]
                        ]
                    }
                ]
            }


            // dom结构
            var dashBoard = echarts.init(document.getElementById('dashBoard'));
            var lineChart = echarts.init(document.getElementById('lineChart'));
            var histogram = echarts.init(document.getElementById('histogram'));
            var radar = echarts.init(document.getElementById('radar'));
            var pie = echarts.init(document.getElementById('pie'));
            var zhengtai = echarts.init(document.getElementById('zhengtai'));

            // 页面加载
            $(function () {
                initialDashBoard();
                initialLineChart();
                initialHistogram();
                initialRadar();
                initialPie();
                initialZhengTai();
            });

            function initialDashBoard() {
                // 基于准备好的dom，初始化仪表盘实例
                dashBoard.setOption(optionDashBoard, true);
            }
            function initialLineChart() {
                // 基于准备好的dom，初始化折线图实例
                lineChart.setOption(optionLineChart, true);
            }
            function initialHistogram() {
                // 基于准备好的dom，初始化柱状图实例
                histogram.setOption(optionHistogram, true);
                //histogram.setOption(option, true);
            }
            function initialRadar() {
                // 基于准备好的dom，初始化雷达实例
                radar.setOption(optionRadar, true);
            }
            function initialPie() {
                // 基于准备好的dom，初始化饼图实例
                pie.setOption(optionPie, true);
            }
            function initialZhengTai() {
                // 基于准备好的dom，初始化饼图实例
                zhengtai.setOption(optionZhengtai, true);
            }


            // 图表自适应大小
            window.onresize = function () {
                dashBoard.resize();
                lineChart.resize();
                histogram.resize();
                radar.resize();
                pie.resize();
                zhengtai.resize();
            }
        };

    </script>
</jsSection>
</body>
</html>