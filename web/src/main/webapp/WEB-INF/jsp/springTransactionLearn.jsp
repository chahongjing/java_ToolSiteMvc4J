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

    .inline {
        display: inline-block;
    }
</style>
<title>学习spring transaction</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div>
    <div>
        <p>注解方式</p>
        <ul>
            <li>添加transactionManager&nbsp;&nbsp;bean</li>
            <li>添加tx:annotation-driven，指定transaction-manager</li>
        </ul>
    </div>
    <div>
        <p>传播协行为（propagation属性）</p>
        <ul>
            <li>required，如果有事务运行就在当前事务下运行，否则开启一个新事务再运行</li>
            <li>required_new，当前方法必须启动新事务，如果有其它事务在运行，其它事务先挂起</li>
            <li>supports，如果有事务运行就在当前事务下运行，否则他可以不运行在事务中</li>
            <li>not_supported，不运行在事务中，如果有事务，则事务挂起</li>
            <li>mandatoy，必须运行在事务中，否则抛异常</li>
            <li>never，不运行在事务中，否则抛异常</li>
            <li>nested，如果有事务，则在这个事务的嵌套事务中运行，否则开启一个新事务</li>
        </ul>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>

<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>