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
        <p>事务配置方式一、注解</p>
        <ul>
            <li>添加transactionManager&nbsp;&nbsp;bean</li>
            <li>添加tx:annotation-driven，指定transaction-manager</li>
            <li>然后就可以使用Transactional注解标记事务
                <ul>
                    <li>propagation，传播行为</li>
                    <li>isolation，隔离级别</li>
                    <li>readonly，指定操作全是读操作，没有写操作，可以提高性能</li>
                    <li>timeout，超时回滚</li>
                </ul>
            </li>
        </ul>
    </div>
    <div>
        <p>事务配置方式二、xml配置</p>
        <ul>
            <li>添加transactionManager的bean</li>
            <li>配置事务属性tx:advice</li>
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
    <div>
        <p>隔离级别（如下级别越来越高）</p>
        <ul>
            <li>READ UNCOMMITTED，读未提交，有可能事务执行到一半还未提交的数据也读取出来</li>
            <li>READ COMMITTED，读已提交的事务的数据，（不可重复读）</li>
            <li>REPEATABLE READ，加行锁，行读取之后，再次读取，数据必定一样</li>
            <li>SERIALIZABLE读的时候加表锁，并发量最低</li>
        </ul>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>

<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>