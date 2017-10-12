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
<title>学习session</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div>
    <p>
    <h2>sessionid</h2>
    会谈状态用来把同一会话中的一系列请求和响应关联起来。同一会话请求会附带同一标识符<br>
    不同的会话请求会附带不同的标识
    <ul>
        <li>1.通过在request中的cookie中设置JSESSIONID</li>
        <li>2.在请求url后面带上JSESSIONID=xxxx</li>
    </ul>
    </p>

</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>

<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>