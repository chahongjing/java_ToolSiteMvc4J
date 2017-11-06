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
<title>学习session</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div>
    <p>
    <h2>sessionid</h2>
    会谈状态用来把同一会话中的一系列请求和响应关联起来。同一会话请求会附带同一标识符<br>
    不同的会话请求会附带不同的标识
    <ul>
        <li>1.通过在request中的cookie中设置JSESSIONID</li>
        <li>2.在请求url后面带上JSESSIONID=xxxx, 通过调用response.encodeURL()或encodeRedirectURL()方法在地址后添加jsessionid, 如果不需要添加jsessionid，则直接返回参数</li>
    </ul>
    </p>
    <p>
    <h2>sessioin创建</h2>
    <ul>
        <li>若当前的jsp或servlet是客户端访问的web应用的第一个资源，且jsp的page指令指定session为false,则不会创建session对象</li>
        <li>若jsp不是客户端访问的第一个资源且其它页面已创建了session对象，则当前页面会直接返回其它页面创建的那个session对象，不会再重新创建</li>
    </ul>
    </p>
    <p>
    <h2>sessioin销毁</h2>
    <ul>
        <li>session.invalidate()</li>
        <li>当前web应用被卸载了</li>
        <li>超过过期时间，以秒为单位，设置过期时间
            <ul>
                <li>代码设置5秒内有效期session.setMaxInactiveInterval(5)</li>
                <li>web.xml设置，tomcat服务器中的web.xml设置默认为30分钟过期，也可以在项目中的web.xml中设置,以为分单位
                <br>
                    &lt;session-config&gt;<br>
                        &lt;session-timeout&gt;30&lt;session-timeout&gt;<br>
                    &lt;session-config&gt;<br>
                </li>
            </ul>
        </li>
    </ul>
    </p>
    <p>
        <h2>session api</h2>
        <ul>
            <li>getId()</li>
            <li>isNew()</li>
            <li>getMaxInactiveInterval()</li>
            <li>getCreationTime()</li>
            <li>getLastAccessedTime()</li>
        </ul>
    </p>

</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>

<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>