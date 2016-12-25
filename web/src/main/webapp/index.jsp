<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include  file="/commonCss.jsp" %>
</head>
<body>
<h2>Hello World!</h2>
<a href="${ctx}/index.do">跳转</a>
<a href="javascript:void(0)" id="lnkTestPromise">测试promise</a>
<a href="javascript:alert('请在单元测试中查看！')" id="lntAddSolrIndex">solr</a>
<br/>
<a href="${ctx}/userinfo/loginpage.do">登录</a>
<a href="javascript:void(0)" id="lnkLogout">注销</a>
<%@ include  file="/commonJs.jsp" %>
<script>
    $(function () {
        $('#lnkLogout').click(function(){
            var result = $.ajax({
                url: ctx + '/userinfo/logout.do'
            });

            result.success(function(data) {
                window.location = ctx;
            });
        });

        $('#lnkTestPromise').one('click', function() {
            console.log('click');
            var p1 = $.ajax({
                url: ctx + '/testP1.do'
            }).then(function(data) {
                console.log('testP1');

                return 'p1';
            });

            var p2 = $.ajax({
                url: ctx + '/redirect.do'
            }).then(function(data) {
                console.log('testP2');

                return 'p2';
            });

            Promise.all([p1, p2]).then(function(result) {
                console.log(result);
            });
        });


    });
</script>
</body>
</html>
