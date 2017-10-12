<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/beginHead.jsp" %>
<%-- 页头，添加title, mate信息, link样式, script脚本(建议在script节中添加) --%>
<style>
    * {
        font-family: "Microsoft YaHei UI";
        font-size: 14px;
    }

    .bold {
        font-weight: bold;
    }

    .red {
        color: #f00;
    }

    .green {
        color: #008000;
    }

    .inline {
        display: inline-block;
    }

    p {
        text-indent: 10px;
    }
</style>
<title>学习cookie</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div>
    <p>
    <h2>bean属性操作</h2>
    可使用beanutils工具类
    </p>

</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>

<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>