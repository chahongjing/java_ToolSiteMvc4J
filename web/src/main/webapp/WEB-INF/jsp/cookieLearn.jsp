<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/beginHead.jsp" %>
<%-- 页头，添加title, mate信息, link样式, script脚本(建议在script节中添加) --%>
<style>
    * {
        font-family: "Microsoft YaHei UI";
        font-size: 14px;
    }

    .bold {
        font-weight: bold;
    }

    .red {
        color: #f00;
    }

    .green {
        color: #008000;
    }

    .inline {
        display: inline-block;
    }

    p {
        text-indent: 10px;
    }
</style>
<title>学习cookie</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div>
    <p>
    <h2>cookie作用范围</h2>
    若没有指定路径，则是<span class='red'>当前url的路径</span>进行存储，子页面可以访问父页面的cookie，<br>
	若想在父页面访问子页面的cookie,则可以把子页面的cookie的path设置短一些，如/ToolSiteMvc4J根目录(cookie.setPath(request.getContextPath()))，<br>
	这样一来，所有页面都可以访问所有cookie;
    </p>
    <p>
    <h2>过期时间</h2>
    <ul>
	<li>0: 设置cookie过期</li>
	<li>正数：过期时间（秒）</li>
	<li>负数或不设置：保存在内存中，浏览器关闭cookie过期</li>
	</ul>
    </p>

</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>

<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>