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
<title>学习spring Aop</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div>
    <div>
        <p>代理模式<a href="<c:url value="/test/testProxy.do" />" target="_blank">测试代理</a></p>
        <ul>
            <li>切面（aspect）</li>
            <li>通知（advice）</li>
            <li>目标（target）</li>
            <li>代理（proxy）</li>
            <li>连接点（joinpoint）</li>
            <li>切点（pointcut）</li>
        </ul>
    </div>
    <div>
        <p>AspectJ<a href="<c:url value="/test/testAspectJ.do" />" target="_blank">测试AspectJ</a></p>
        <ul>
            <li>@Before前置通知，方法执行前</li>
            <li>@After后置通知，方法执行后, 无论是否发生异常，都会执行</li>
            <li>@AfterReturning返回通知，方法正常结束后执行</li>
            <li>@AfterThrowing异常通知，抛出异常后执行</li>
            <li>@Around环绕通知，围绕方法执行</li>
        </ul>
        <p>可使用@Order(3)指定切面优先级，值越小，优先级越高，越先执行</p>
    </div>
    <div>
        <p>基于xml配置文件的配置，此时切面中不需要任何注解，包括@Aspect, @Before等</p>
        <ul>
            <li>在配置文件中配置切面bean, 如<br>&lt;bean id="loggingAspect" class="com.zjy.bll.common.LoggingAspect"&gt;&lt;/bean&gt;</li>
            <li>配置aop<br>
                &lt;aop:config&gt;<br>
                &nbsp;&nbsp;&lt;!-- 配置切点表达式 --&gt;<br>
                &nbsp;&nbsp;&lt;aop:pointcut expression="execution(* com.zjy.bll.service.*.*(..))" id="pointcut"&gt;&lt;/aop:pointcut&gt;<br>
                &nbsp;&nbsp;&lt;aop:aspect ref="loggingAspect" order="2"&gt;<br>
                &nbsp;&nbsp;&nbsp;&nbsp;&lt;aop:before method="beforeMethod" pointcut-ref="pointcut"&gt;&lt;/aop:before&gt;<br>
                &nbsp;&nbsp;&nbsp;&nbsp;&lt;aop:after-throwing method="afterThrowing" pointcut-ref="pointcut" throwing="ex"&gt;&lt;/aop:after-throwing&gt;<br>
                &nbsp;&nbsp;&lt;/aop:aspect&gt;
                &lt;/aop:config&gt;<br>
            </li>
        </ul>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>

<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>