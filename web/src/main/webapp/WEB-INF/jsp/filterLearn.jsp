<%@ page import="com.zjy.entities.UserInfo" %>
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

    .inline {
        display: inline-block;
    }
</style>
<title>学习filter</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div>
    <p>
		<h3>对请求前后进行特殊处理</h3>
		<ul>
			<li>三个接口Filter, FilterConfig, FilterChain</li>
			<li>在web.xml中配置并映射filter</li>
			<li>实现三个方法
			    <ul>
					<li>public void init(FilterConfig filterConfig),filter加载当前应用时执行一次，且只被执行一次, 是单例的</li>
					<li>public void doChain(HttpServletRequest request, HttpServletResponse response, FilterChain chain)</li>
					<li>public void destory(),filter销毁前执行，且只执行一次</li>
				</ul>
			</li>
			<li>多个filter拦截顺序（不是创建顺序）是和web.xml中配置的&lt;filter-mapping&gt;节点的顺序一致</li>
		</ul>
    </p>
</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>

<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>