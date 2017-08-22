<%@ page import="org.apache.commons.lang3.exception.ExceptionUtils" %><%--
  Created by IntelliJ IDEA.
  User: chahongjing
  Date: 2016/12/10
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%
    Throwable throwable = ((Throwable)request.getAttribute("javax.servlet.error.exception"));
    Throwable rootCause = ExceptionUtils.getRootCause(throwable);
%>
<html>
<head>
    <title><%= rootCause.getMessage() %></title>
</head>
<body>
<h2><%= rootCause.getMessage() %></h2>
<p><%= rootCause.getStackTrace() %></p>

</body>
</html>
