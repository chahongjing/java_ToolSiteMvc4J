<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>maven学习</title>
</head>
<body>
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
<jsSection>

</jsSection>
</body>
</html>