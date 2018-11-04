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
			<li>一般有三个概念，一：抽象规范；二:一个具体的实现；三：一个运行中的虚拟机实例</li>
			<li>jvm内部有两种线程：守护线程和非守护线程，守护线程通常由虚拟机自己使用，如执行垃圾收集任务，jsva程序也可以把它创建的任何线程标记为守护线程。
			只要有任何守护线程在在运行，那java程序也在继续运行，虚拟机存活，如果所有非守护线程都终止时，虚拟机将自动退出。java程序入口main线程是非守护线程
			，因此main执行完成后虚拟机将退出。</li>
			<li>.class文件由类加载器加载，然后通过执行引擎把字节码编译成本地可执行代码，一般有三种模式，一次性编译，即时编译（用多少编译多少），自适应监控使用频率，高频率的本地代码被缓存</li>
			<li>启动类装载器，用户自定义装载器<br>
			<ul><li>
				启动类装载器：是jvm实现的一部分
			</li>
				<li>
					自定义装载器：是java程序的一部分
				</li>
			</ul></li>
		</ul>
    </p>
    <p>
		<h2>java虚拟机</h2>
		<ul class="list">
			<li>程序计数器，执行下一条指令的地址</li>
			<li>java栈，存储栈桢，如每调用一个方法，都会创建一个桢，存储局部变量，返回值等</li>
			<li>本地方法栈，和java栈类似，执行native method服务</li>
			<li>堆，当前虚拟机所有线程共享，程序中创建的对象放在这里</li>
			<li>方法区：存储类信息，名称， 方法，字段等，及静态变量，常量，常量池，当前虚拟机所有线程共享，.class文件解析出来的类信息就存储在这里</li>
        <li>直接内存区</li>
        <li>堆中可分为新生代，老年代，永久代（和方法区相似，1.8被移除，放在metaspace）；新生代分为eden, s0, s1</li>
        <li>eden区回收频率高，效率高，可回收70%~90%对象，剩下对象进入s0,每回收一次，s0所有幸存对象放到s1，s0和s1身份互换，再次回收
        s1所有幸存对象放到s0，回收次数过多（一般为年龄15次后进入老年代，大对象一般也放在老年代）</li>
        <li>热点探测法，java代码jit即时编译，如果次数达到一定次数则会进行缓存，提高性能</li>
        <li>1.引用计数法，程序会暂停，2.复制法，3.标记清除法，4.标记整理法</li>
    </ul>
    </p>
    <p>
		<h2>垃圾收集器</h2>
		<ul class="list">
			<li>引用计数收集器。每个对象都有引用计数。但无法检测出循环</li>
			<li>跟踪收集器，（标记清除法）</li>
			<li>压缩收集器</li>
			<li>复制收集器</li>
			<li>按代收集</li>
		</ul>
    </p>
    <p>
		<h2>类加载</h2>
		<ul class="list">
			<li>装载，连接，初始化，其中，连接包括验证，准备，解析（可选）</li>
			<li>java栈，存储栈桢，如每调用一个方法，都会创建一个桢，存储局部变量，返回值等</li>
			<li>本地方法栈，和java栈类似，执行native method服务</li>
			<li>堆，当前虚拟机所有线程共享，程序中创建的对象放在这里</li>
			<li>方法区：存储类信息，名称， 方法，字段等，及静态变量，常量，常量池，当前虚拟机所有线程共享，.class文件解析出来的类信息就存储在这里</li>
		</ul>
    </p>
</div>
<jsSection>
    <script>
    </script>

</jsSection>
</body>
</html>