<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/beginHead.jsp" %>
<%-- 页头，添加title, mate信息, link样式, script脚本(建议在script节中添加) --%>
<style>
</style>
<title>tomcat学习</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div>
    <p>1. work目录存放jsp经过编译后对应的servlet class文件，lib目录存放程序使用的公共的jar包<br>
    server.xml中也可以添加context,但一般不这样做，因为这个文件只有tomcat重新启动时才会重新加载，<br>
        所以一般添加在context.xml中，tomcat会定时扫描这个文件，发现时间戳变化后会动态加载
    </p>
    <p>2. server.xml server节点下有多个listener和多个service节点，每个service下有多个connector和一个engine节点， engin下又有host节点</p>
    <p>3. startup.bat通过找到并运行同目录下的catalina.bat启动的 catalina run在本cmd窗口打开服务器，catalina start在新cmd窗口打开服务器</p>
</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>

<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>