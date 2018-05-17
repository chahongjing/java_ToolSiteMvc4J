<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
    <%@ include file="/WEB-INF/jsp/common/commonCss.jsp" %>
    <link href="${ctx}/bootstrap/css/bootstrap.css" rel="stylesheet" />
    <link href="${ctx}/bootstrap/css/font-awesome.css" rel="stylesheet" />
    <link href="${ctx}/bootstrap/css/main.css" rel="stylesheet" />
    <style>
        body{padding:20px;}
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
        a{display:inline-block;margin-right:10px;}
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
<a href="${ctx}/index/">跳转</a>
<a href="${ctx}/userinfo/loginindex.do?code=zjy&name=曾&name=军" target="_blank">分页查询</a>
<a href="javascript:void(0)" id="lnkTestPromise">测试promise</a>
<a href="javascript:alert('请在单元测试中查看！')" id="lntAddSolrIndex">solr</a>
<a href="${ctx}/test/test.do">测试@Value和加载部分页</a>
<br/>
<br/>
<form method="post" action="${ctx}/test/testPostWithFile.do" enctype="multipart/form-data" target="_blank">
    <input type="text" name="name"/>
    <input type="hidden" name="age" value="28"/>
    <input type="text" name="test" value="测试部分页render:${username}"/>
    <input type="file" name="myfile" multiple="multiple" />
    <button type="submit" name="tj" value="提交">提交</button>
    <button type="button" name="ajaxtj" value="提交">ajax提交</button>
</form>
<br />
<hr>
java<br>
<a href="${ctx}/learn/download.do">下载</a>
<a href="${ctx}/learn/servletLearn.do" target="_blank">学习servlet</a>
<a href="${ctx}/learn/jspLearn.do?arr=1&arr=2&userName=曾军毅" target="_blank">学习jsp</a>
<a href="${ctx}/learn/elLearn.do" target="_blank">学习el</a>
<a href="${ctx}/learn/springAopLearn/123.do" target="_blank">学习spring aop</a>
<a href="${ctx}/learn/springBeanLearn/123.do" target="_blank">学习spring Bean</a>
<a href="${ctx}/learn/springMVCLearn/123.do" target="_blank">学习springMVC</a>
<a href="${ctx}/learn/cookieLearn.do" target="_blank">学习cookie</a>
<a href="${ctx}/learn/sessionLearn.do" target="_blank">学习session</a>
<a href="${ctx}/learn/javaLearn.do" target="_blank">java后台学习</a>
<a href="${ctx}/learn/filterLearn.do" target="_blank">filter学习</a>
<a href="${ctx}/learn/springTransactionLearn.do" target="_blank">事务学习</a>
<a href="${ctx}/learn/nioLearn.do" target="_blank">nio学习</a>
<a href="${ctx}/learn/shiroLearn.do" target="_blank">shiro学习</a>
<a href="${ctx}/learn/reflectLearn.do" target="_blank">反射学习</a>
<a href="${ctx}/learn/encryptLearn.do" target="_blank">加密学习</a>

<hr>
服务器<br>
<a href="${ctx}/learn/tomcatLearn.do" target="_blank">学习tomcat</a>
<hr>
maven<br>
<a href="${ctx}/learn/mavenLearn.do" target="_blank">maven学习</a>
<hr>
echarts<br>
<a href="${ctx}/echarts/index.do" target="_blank">echarts图表</a>
<hr>
angular<br>
<a href="${ctx}/learn/testangular.do" target="_blank">测试angular</a>
<a href="${ctx}/learn/angulardemo.do" target="_blank">angular demo</a>
<hr>
vue<br>
<a href="${ctx}/learn/vueDemo.do" target="_blank">vue demo</a>
<a href="${ctx}/learn/vueLearn.do" target="_blank">vue learn</a>
<hr>
ueditor<br>
<a href="${ctx}/learn/ueditorLearn.do" target="_blank">ueditor</a>
<hr>
js<br>
<a href="${ctx}/learn/jsLearn.do" class="btn btn-success btn-app radius-4" target="_blank">
    <i class="icon-cog fa fa-lastfm bigger-230" style="display:block;"></i>
    js学习
</a>
<hr>
其它<br>
<a href="${ctx}/learn/gitLearn.do" target="_blank">git学习</a>
<a href="${ctx}/learn/otherLearn.do" target="_blank">其它</a>
<a href="${ctx}/tool/tableToObject.do" target="_blank">oracle表转类</a>
<a href="${ctx}/tool/sqlGenerate.do" target="_blank">sql生成器</a>

<br />
<br />
<br />
<a href="${ctx}/cost/.do" target="_blank">财务管理</a>

<br/>
<br/>
<label class="switch">
    <input type="checkbox" class="hide" />
    <div>
        <span>ON</span>
        <span></span>
        <span>OFF</span>
    </div>
</label>
<br/>
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
                url: ctx + '/test/testP2.do'
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
