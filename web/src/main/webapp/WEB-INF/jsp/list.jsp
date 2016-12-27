<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>列表</title>
    <%@ include file="/commonCss.jsp" %>
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

<%@ include file="/commonJs.jsp" %>
</body>
</html>