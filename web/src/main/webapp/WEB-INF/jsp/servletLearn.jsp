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
</style>
<title>学习jsp</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div>
    生命周期函数:
    <ul>
        <li>constructor: 创建时，只调用一次</li>
        <li>init(ServletConfig)：初始化时，只调用一次</li>
        <li>service：可被调用多次，用于处理用户请求</li>
        <li>destory：关闭时，只调用一次</li>
    </ul>
    <br>
    load-on-startup，值越小越先启动；若小于0，则在调用时才会启动
    <br>
    <ul>
        <li>ServletConfig:封装了servlet信息，并可以获取ServletContext对象，还包含在配置文件中添加的init-param（必须放在load-on-startup之前）信息.
            servletConfig.getInitParameter("paramName")
        </li>
        <li>ServletContext:当前应用。即application对象。
            <ul>
                <li>可获取当前web应用的初始化参数（非servlet初始化参数）servletContext.getInitParameter("paramName")</li>
                <li>获取服务器上文件路径：getRealPath("/note.txt");如: <span class="green">E:\ToolSiteMvc4J\note.txt</span></li>
                <li>获取应用名称: getContextPath();如：<span class="green">/ToolSiteMvcjJ</span></li>
                <li>getResourceAsStream(String path);path是从web的根目录开始寻找
                    <ul>
                        <li>相比较:getClass().getClassLoader().getResourceAsString("");在classes中寻找</li>
                    </ul>
                </li>
            </ul>
        </li>
        <li>
            HttpServletRequest:
            <ul>
                <li>getParameter:获取请求参数,get,post(普通form表单提交)</li>
            </ul>
        </li>
    </ul>
</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>

<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>