<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/common/commonCss.jsp" %>
    <style>
        .font {
            font-family: arial;
            font-weight: 500;
        }
        .next{display:inline-block;width:100px;height:46px;background-color:#D0DCED;margin:0;padding:0;border:0;float:left;position:relative;}
        .next:before{
            position:absolute;top:0px;right:-25px;
            z-index: 1;
            content:'';border:23px solid black;border-right:none;
            border-color: transparent transparent transparent white;
        }
        .next:after{
            position:absolute;top:0;right:-23px;
            z-index: 1;
            content:'';border:23px solid white;border-right:none;
            border-color: transparent transparent transparent #D0DCED;
        }
        .pro:last-child{border:1px solid black;}
        .pro span:last-child:before,.pro span:last-child:after{display:none;}
    </style>
</head>
<body>
<h2>Hello World!</h2><span class="font"><% out.println("<div>abc</div>"); %></span>
<br/>
<br/>
<a href="${ctx}/userinfo/loginpage.do">登录</a>
<a href="javascript:void(0)" id="lnkLogout">注销</a>
<br/>
<br/>
<a href="${ctx}/index.do">跳转</a>
<a href="${ctx}/userinfo/loginindex.do?code=zjy&name=曾&name=军" target="_blank">分页查询</a>
<a href="javascript:void(0)" id="lnkTestPromise">测试promise</a>
<a href="javascript:alert('请在单元测试中查看！')" id="lntAddSolrIndex">solr</a>
<a href="${ctx}/test/test.do">测试@Value和加载部分页</a>
<br/>
<br/>
<form method="post" action="${ctx}/test/fileupload.do" enctype="multipart/form-data" target="_blank">
    <input type="text" name="name"/>
    <input type="text" name="test" value="测试部分页render:${username}"/>
    <input type="file" name="myfile" multiple="multiple" />
    <button type="submit" name="tj" value="提交">提交</button>
    <button type="button" name="ajaxtj" value="提交">ajax提交</button>
</form>
<br />
<br />
<a href="${ctx}/test/download.do">下载</a>
<a href="${ctx}/echarts/index.do" target="_blank">echarts图表</a>
<a href="${ctx}/test/testangular.do" target="_blank">测试angular</a>
<a href="${ctx}/test/angulardemo.do" target="_blank">angular demo</a>
<br/><br/>
<a href="${ctx}/test/tomcatLearn.do" target="_blank">学习tomcat</a>
<a href="${ctx}/test/servletLearn.do" target="_blank">学习servlet</a>
<a href="${ctx}/test/jspLearn.do?arr=1&arr=2" target="_blank">学习jsp</a>
<a href="${ctx}/test/elLearn.do" target="_blank">学习el</a>
<a href="${ctx}/test/springLearn/123.do" target="_blank">学习spring</a>

<br />
<br />
<br />
<a href="${ctx}/cost/.do" target="_blank">财务管理</a>

<br />
<div class="pro">
<span class="next"></span>
<span class="next"></span>
<span class="next"></span>
<span class="next"></span>
</div>
<br />
<%@ include file="/WEB-INF/jsp/common/commonJs.jsp" %>
<script>
    $(function () {
        $('#lnkLogout').click(function () {
            var result = $.ajax({
                url: ctx + '/userinfo/logout.do'
            });

            result.success(function (data) {
                window.location = ctx;
            });
        });

        $('#lnkTestPromise').one('click', function () {
            console.log('click');
            var p1 = $.ajax({
                url: ctx + '/test/testP1.do'
            }).then(function (data) {
                console.log('testP1');

                return 'p1';
            });

            var p2 = $.ajax({
                url: ctx + '/test/redirect.do'
            }).then(function (data) {
                console.log('testP2');

                return 'p2';
            });

            Promise.all([p1, p2]).then(function (result) {
                console.log(result);
            });
        });

        $('button[name=ajaxtj]').click(function() {
            var formData = new FormData();
            var files = $('input[name=myfile]')[0].files;
            formData.append("name", "zjy");
            for(var i = 0; i < files.length; i++) {
                formData.append("myfile", files[i]);
            }

            $.ajax({
                url: ctx + '/test/fileupload.do',
                type: 'post',
                processData: false,
                contentType: false,
                data: formData,
                success: function(resp) {

                }
            });
        });
    });
</script>
</body>
</html>
