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

    <div>
        <p>tomcat启动过程</p>
        <ul>
            <li>tomcat最顶层容器，代表整个服务器，一般只有一个server，server有一个或多个service，service分为两部分，connector和container</li>
            <li>service中可以有多个connector, 用于处理requet,response,socket的转换如http和https,container用于封装和管理servlet，一个service中只有一个container</li>
            <li>tomcat中的server由org.apache.catalina.startup.Catalin来管理，Catalina是整个Tomcat的管理类，他里面有三个load, start, stop 分别用来管理整个服务器的生命周期</li>
            <li>load方法用于 根据conf/server.xml文件创建server并调用server的init方法进行初始化，start方法用于启动服务器，stop方法用于停止服务器地，他们分别调用server的start和stop方法</li>
            <li>这三个方法都会按容器的结构逐层调用相应的方法，比如server里的start方法会调用所有service中的start方法</li>
            <li>而service中的start又会调用它下面connector和container中的start方法，这样，tomcat就启动了，init和stop方法也类似</li>
            <li>另外Catalina中await方法比较特殊，它是进入一个循环，让主线程不会退出</li>
            <li>tomcat的main方法并不在Catalina中，而是在org.apache.catalina.startup.BootStrap，类似于CatalinaAdaptor，把入口和具体的管理类分开，
                可以很方便的创建出多种启动方式，每种启动方式只需要写一个相应的CatalinaAdaptor就可以了</li>
        </ul>
    </div>
    <hr>
    <div>
        <p>Bootstrap启动过程</p>
        <ul>
            <li>BootStrap是tomcat的入口，正常情况启动tomcat就是启用了Bootstrap的main方法</li>
        </ul>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>

<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>