<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://zjy.com" prefix="myPre"%>

<html>
<head>
    <title>列表</title>
    <%@ include file="/WEB-INF/jsp/common/commonCss.jsp" %>
</head>
<body>

<table>
    <c:forEach var="item" items="${pageinfo.list}" varStatus="status">
        <tr <c:if test="${status.count%2 == 0}">bgcolor="#CCCCFE"</c:if> align="left">
            <td>
                <c:out value="${item.userName}"></c:out>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>el学习</h2>
<jsp:useBean id="userTest2" class="com.zjy.entities.UserInfo" scope="session"></jsp:useBean>
<jsp:setProperty name="userTest2" property="userName" value="曾军毅jsp获取数据"></jsp:setProperty>

jsp获取数据：${sessionScope.userTest2.userName}<br>
controller获取数据：${sessionScope.userTest.userName}
<br>
param<br>
code:${param.code}
<br>
name:${paramValues.name[0]},${paramValues.name[1]}

empty:${empty " "}

<p>c:url</p>
<c:url value="/test/a.html"></c:url>

<h2>el tag</h2>
<myPre:readFile src="/WEB-INF/myTag.tld">
从页面上传过来的参数：${param.name}
</myPre:readFile>

<%@ include file="/WEB-INF/jsp/common/commonJs.jsp" %>
</body>
</html>