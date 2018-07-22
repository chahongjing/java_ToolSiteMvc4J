<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
<input type="button" value="添加" @click="addUser()"/>


<jsSection>
    <script>
        vueData = {};

        vueMethods = {
            addUser: addUser
        };

        function addUser(){
            this.commonSrv.get("/comm/getId").then(function(resp) {
                if (resp.data.status == Constant.AjaxStatus.OK) {
                    window.location = ctx + '/userinfo/userEdit.do?userGuid=' + resp.data.value;
                } else {
                    alert(resp.data.message);
                }

            });
        }
    </script>
</jsSection>
</body>
</html>
