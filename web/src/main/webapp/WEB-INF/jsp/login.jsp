<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
    <head>
        <title>test</title>
        <%@ include  file="/WEB-INF/jsp/common/commonCss.jsp" %>
    </head>
    <body>
    <fmt:message key="username">
        <fmt:param value="a"/>
        <fmt:param value="b"/>
    </fmt:message>
    <form id="loginForm" action="${ctx}/userinfo/login.do" method="post">
        <input type="hidden" id="redirectUrl" value="<c:out value="${redirectUrl}"/>" />
        <input type="text" name="userCode" value="admin"/>
        <input type="password" name="password" value="1" />

        <input type="button" value="登 录" id="btnLogin" />
    </form>

    <%@ include  file="/WEB-INF/jsp/common/commonJs.jsp" %>
    <script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
    <script>
        $(function() {
            $('#btnLogin').click(function() {
                $("#loginForm").ajaxSubmit({
                    dataType: "json",
                    beforeSubmit: function (arr, $form, options) {
                        var userName = $("input[name=userCode]");
                        var password = $("input[name=password]");

                        if ($.trim(userName.val()) == "") {
                           alert('请输入用户名!');
                            return false;
                        }
                        if ($.trim(password.val()) == "") {
                            alert('请输入密码!');
                            return false;
                        }
                    },
                    success: function (data) {
                        if (data.status == Constant.AjaxStatus.OK) {
                            var url = $("#redirectUrl").val();
                            url = url ? url : "/Login1.aspx";
                            window.location = url;
                        } else if (data.status == Constant.AjaxStatus.NO) {
                            alert(data.message);
                        }
                    }
                });
            });
        });
    </script>
    </body>
</html>