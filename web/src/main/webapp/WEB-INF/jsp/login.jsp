<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>用户登录</title>
    <%@ include file="/WEB-INF/jsp/common/commonCss.jsp" %>
    <link href="${ctx}/bootstrap/css/bootstrap.css" rel="stylesheet"/>
    <link href="${ctx}/bootstrap/css/font-awesome.css" rel="stylesheet"/>
    <link href="${ctx}/Controls/Dialog.css" rel="stylesheet"/>
    <style>
        html,body {background: #dfe0e2 url('${ctx}/bootstrap/images/pattern.jpg') repeat;margin:0;padding:0;width:100%;height:100%;
        }
        .login-content {
            width:400px;height:360px;position:absolute;top:50%;left:50%;margin-left:-200px;margin-top:-250px;
        }
        .login-content h1 {font-family:"Open Sans","Helvetica Neue",Helvetica,Arial,sans-serif;color:#777;text-align:center;
            font-size:32px;
        }
        .green {color:#69aa46;
        }
        .red {color:#dd5a43;
        }
        .login-content .copy-right {text-align:center;font-size:18px;margin:10px 0 30px;
        }
        .blue {color:#478fca;
        }
        .login-content .login-form{box-shadow:rgba(0,0,0,0.15) 0 0 2px 1px;padding:20px 35px;
            background-color:#f7f7f7;width:400px;height:260px;}
        .login-form .header {font-size:19px;margin-top:15px;
        }
        .form-control {height:34px;width:100%;border:none;position:relative;margin-top:25px;
        }
        .form-control input {width:100%;height:100%;height:34px;position:absolute;left:0;top:0;outline:none;
            text-indent:10px;font-size:14px;border:1px solid #d5d5d5;line-height:34px;
            transition: 0.2s;-moz-transition: 0.2s;-webkit-transition: 0.2s;-o-transition: 0.2s;
        }
        .form-control input:hover {
            border-color: #b5b5b5;
        }

        .form-control input:focus {
            border-color: #f59942;
        }
        .form-control i {position:absolute;right:10px;top:10px;color:#55595c;
        }
        .form-button {
            margin-top:30px;
        }
        .form-button button {background-color:#428bca;width:100px;height:35px;border:4px solid #428bca;border-radius:0;
            transition: 0.2s;-moz-transition: 0.2s;-webkit-transition: 0.2s;-o-transition: 0.2s;
        }
        .form-button button:hover {background-color:#025aa5;border-color:#428bca;
        }
    </style>
</head>
<body>
<div class="login-content">
    <h1>
        <i class="fa fa-leaf green"></i><span class="red">后台管理</span><span class="grey" id="id-text2">系统</span>
    </h1>
    <h4 class="copy-right blue">&copy; Zjy Office</h4>
    <div class="login-form">
        <form id="formLogin" action="${ctx}/userinfo/login" method="post">
            <input type="hidden" id="RedirectUrl" name="RedirectUrl" value="<c:out value="${redirectUrl}"/>"/>
           <h4 class="header blue"><i class="fa fa-coffee green"></i>请输入您的信息</h4>
            <div class="form-control"><input type="text" name="UserCode" maxlength="30" placeholder="请输入账户"
                                             autofocus/><i class="fa fa-user"></i></div>
            <div class="form-control"><input type="password" name="Password" maxlength="30" placeholder="请输入密码"/><i
                    class="fa fa-lock"></i></div>
            <div class="form-button">
                <button type="button" class="pull-right btn btn-sm btn-primary" id="btnLogin" onclick="login()">
                    <i class="fa fa-key"></i><span class="bigger-110">登 录</span>
                </button>
            </div>
        </form>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/common/commonJs.jsp" %>
<script src="${ctx}/Controls/Dialog.js"></script>
<script src="${ctx}/js/jquery.form.js"></script>
<script>
    $(function () {
        $('.form-control i').click(function () {
            $(this).siblings('input').focus();
        });
        $('input[name=UserCode], input[name=Password]').bind('keypress', function (event) {
            if (event.keyCode == "13") {
                $("#btnLogin").click();
            }
        });
    });
    // 函数名称： login
    // 函数功能： 外部客户登录
    // 函数参数： 无
    // 返 回 值： 无
    // 创 建 人： zengjy01
    // 创建日期： 2014-03-25 10:19:27
    var login = function () {
        $("#formLogin").ajaxSubmit({
            dataType: "json",
            beforeSubmit: function (arr, $form, options) {
                var userName = $("input[name=UserCode]");
                var password = $("input[name=Password]");

                if ($.trim(userName.val()) == "") {
                    window.DialogBox.Alert('请输入用户名!', function () {
                        return false;
                    });
                    return false;
                }
                if ($.trim(password.val()) == "") {
                    window.DialogBox.Alert('请输入密码!', function () {
                        return false;
                    });
                    return false;
                }
            },
            success: function (data) {
                if (data.status == Constant.AjaxStatus.OK) {
                    var url = $("#RedirectUrl").val();
                    url = url ? url : "/Login1.aspx";
                    window.location = url;
                } else if (data.status == Constant.AjaxStatus.NO) {
                    window.DialogBox.Alert(data.message, function () {
                        return false;
                    });
                }
            },
            error: function() {}
        });
    }
</script>

<fmt:message key="username">
    <fmt:param value="a"/>
    <fmt:param value="b"/>
</fmt:message>
</body>
</html>