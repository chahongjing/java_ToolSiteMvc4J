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
<title>学习spring</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div>
    <p>代理模式<a href="<c:url value="/test/testProxy.do" />" target="_blank">测试代理</a></p>
    <ul>
        <li>切面（aspect）</li>
        <li>通知（advice）</li>
        <li>目标（target）</li>
        <li>代理（proxy）</li>
        <li>连接点（joinpoint）</li>
        <li>切点（pointcut）</li>
    </ul>
    <p>AspectJ<a href="<c:url value="/test/testAspectJ.do" />" target="_blank">测试AspectJ</a></p>
    <ul>
        <li>@Before前置通知，方法执行前</li>
        <li>@After后置通知，方法执行后</li>
        <li>@AfterRunning返回通知，返回结果后执行</li>
        <li>@AfterThrowing异常通知，抛出异常后执行</li>
        <li>@Around环绕通知，围绕方法执行</li>
    </ul>
</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>

<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>