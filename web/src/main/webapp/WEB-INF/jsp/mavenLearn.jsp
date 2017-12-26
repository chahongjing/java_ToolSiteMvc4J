<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/beginHead.jsp" %>
<%-- 页头，添加title, mate信息, link样式, script脚本(建议在script节中添加) --%>
<style>
</style>
<title>maven学习</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div>
    <div>
        <p>
            maven命令
        </p>
        <ul>
            <li>
                mvn install -DskipTests，不执行测试用例，但编译测试用例类生成相应的class文件至target/test-classes下
            </li>
            <li>mvn install -Dmaven.test.skip=true，不执行测试用例，也不编译测试用例类。</li>
            <li>mvn test -compile，编译测试程序</li>
            <li>mvn clean</li>
            <li>mvn jar:jar，只打jar包</li>
        </ul>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>
<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>