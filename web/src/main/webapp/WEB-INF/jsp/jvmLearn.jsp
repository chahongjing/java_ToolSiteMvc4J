<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>jvm js</title>
    <style>
        .list{padding-left:20px;}
        .list, .list li{
            list-style-type:circle;
        }
        .list li{padding:5px 0;}
    </style>
</head>
<body>
<%-- html正文 --%>
<div>
    <p>
    <h2>java虚拟机</h2>
    <ul class="list">
        <li>.class文件由类加载器加载，然后通过执行引擎把字节码编译成本地可执行代码，一般有三种模式，一次性编译，即时编译（用多少编译多少），自适应监控使用频率，高频率的本地代码被缓存</li>
		<li>启动类装载器，用户自定义装载器</li>
    </ul>
    </p>
    <p>
    <h2>java虚拟机</h2>
    <ul class="list">
        <li>程序计数器，执行下一条指令的地址</li>
        <li>java栈，存储栈桢，如每调用一个方法，都会创建一个桢，存储局部变量，返回值等</li>
        <li>本地方法栈，和java栈类似，执行native method服务</li>
        <li>堆</li>
        <li>方法区：存储类信息，名称， 方法，字段等，及静态变量，常量，常量池</li>
    </ul>
    </p>
</div>
<jsSection>
    <script>
    </script>

</jsSection>
</body>
</html>