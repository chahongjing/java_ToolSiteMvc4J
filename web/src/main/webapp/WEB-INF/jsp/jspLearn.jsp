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

</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>

<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>