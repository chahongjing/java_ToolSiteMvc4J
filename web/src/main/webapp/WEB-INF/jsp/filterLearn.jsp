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
            <li>在filter-mapping中可配置&lt;dispatcher&gt;节点中配置request或foward或include或error，如果想配置多个，则要多个dispatcher节点</li>
		</ul>
    </p>
    <p>
        <h3>HttpFilter</h3>
        <ul>
            <li>类似HttpServlet</li>
            <li>常用方法
                <ul>
                    <li>使浏览器不缓存页面<br>
                        1. response.setDataHeader("Expires", -1);<br>
                        2. response.setHeader("Cache-Control", "no-cache");<br>
                        3. response.setHeader("Pragma", "no-cache")</li>
                </ul>
                <ul>
                    <li>字符编码过滤器<br>
                        1. request.setCharacterEncoding(StandardCharsets.UTF_8.name());<br>
                        2. 要设置的编码可以从filterconfig中获取servletcontext，然后再获取配置中指定的编码</li>
                </ul>
                <ul>
                    <li>验证用户登录<br>
                        1. urls:放行url,redirectUrl:登录页面,servletPath:request.getServletPath()<br>
                        2. if(urls.contains(servletPath)) {filterChain.doFilter(request, response);return;}<br>
                        3. if(session.getAttribute("loginSessionKey") == null) {response.setRedirect(request.getContextPath() + redirectUrl);}<br>
                        4. 说明已登录, 则直接执行filterChain.doFilter(request, response);
                    </li>
                </ul>
            </li>
        </ul>
    </p>
</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>

<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>