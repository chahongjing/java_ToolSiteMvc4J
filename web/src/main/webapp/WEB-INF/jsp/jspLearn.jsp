<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/beginHead.jsp" %>
<%-- 页头，添加title, mate信息, link样式, script脚本(建议在script节中添加) --%>
<style>
    *{font-family: "Microsoft YaHei UI";font-size:14px;}
    .bold{
        font-weight:bold;}
    .red{color:#f00;}
    .inline{display:inline-block;}
</style>
<title>学习jsp</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div>
    <pre class="inline">out.pringln(myBean.getName());</pre> 相当于 &lt;%= <pre class="inline">myBean.getName()</pre> %&gt;
    <br>
    引擎先把jsp预生成为一个servlet（name_jsp.java）文件，在tomcat中work目录下
    <br/>
    <ul>
        <li>
            &lt;%@ include file="list.jsp"%&gt;:静态包含，只会生成一个servlet
        </li>
        <li>
            &lt;/jsp:include&gt;:动态包含，会生成两个servlet
        </li>
    </ul>
    <br/><br/>
    <h3>九大内置对象</h3>
    <ul>
        <li>application(ServletContext)</li>
        <li>session(HttpSession)</li>
        <li>request(HttpSevletRequest)</li>
        <li>response(HttpResponse)</li>
        <li>page(this)</li>
        <li>out(JspWriter)</li>
        <li>pageContext(PageContext)</li>
        <li>config(ServletConfig)</li>
        <li>exception</li>
    </ul>
    <br>
    <%
        pageContext.setAttribute("name", "PageContext");
        request.setAttribute("name", "HttpSevletRequest");
        session.setAttribute("name", "HttpSession");
        application.setAttribute("name", "ServletContext");
    %>
    其中application(<%= application.getAttribute("name") %>) &gt; session(<%= session.getAttribute("name") %>)
    &gt; request(<%= request.getAttribute("name") %>) &gt; pageContext(<%= pageContext.getAttribute("name") %>)
    <br>他们都有setAttribute方法
    <br><br>
    在jsp中可以定义页面类的属性，还可以定义方法, 要使用&lt;%<span class="bold red">!</span> <pre class="inline">int a = 1;</pre> %&gt;
    <br>
    &lt;%<span class="bold red">!</span> <pre class="inline">public void myFunc() { ... }</pre> %&gt;
    <br/><br/>

    <h3>jsp标签</h3>
    <br>
    &lt;jsp:include page="a.jsp"&gt;&lt;/jsp:include&gt;<br><br>
    &lt;jsp:forward page="a.jsp"&gt;<br>
    &nbsp;&nbsp;&lt;jsp:param name="" value=""&gt;<br>&nbsp;&nbsp;&lt;/jsp:param&gt;
    <br>&lt;/jsp:forward&gt;<br>相当于requestDispatcher.forward(request, response); include也可以添加jsp:param, 用request.getParameter获取

    <br><br>
    <h3>中文乱码</h3>
    <ul>
        <li>pageEncoding=utf-8</li>
        <li>contentType=text/html;charset=UTF-8</li>
        <li>对于post请求，在获取参数之前调用request.setCharacterEncoding("UTF-8")</li>
        <li>对于get请求，在tomcat中conf/server.xml中找到节点Connector，添加属性useBodyEncodingForURI设置为true（此方法将使用post请求中保持一致的编码）<br>
        还可以直接设置为URIEncoding=UTF-8,这只对get有效</li>
    </ul>
</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>

<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>