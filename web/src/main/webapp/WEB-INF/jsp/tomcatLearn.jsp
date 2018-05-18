<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>tomcat学习</title>
</head>
<body>
<div>
    <p>1. work目录存放jsp经过编译后对应的servlet class文件，lib目录存放程序使用的公共的jar包<br>
        server.xml中也可以添加context,但一般不这样做，因为这个文件只有tomcat重新启动时才会重新加载，<br>
        所以一般添加在context.xml中，tomcat会定时扫描这个文件，发现时间戳变化后会动态加载
    </p>
    <p>2. server.xml server节点下有多个listener和多个service节点，每个service下有多个connector和一个engine节点， engin下又有host节点</p>
    <p>3. startup.bat通过找到并运行同目录下的catalina.bat启动的 catalina run在本cmd窗口打开服务器，catalina start在新cmd窗口打开服务器</p>

    <h2>看透spring mvc</h2>
    <div>
        <p>tomcat启动过程</p>
        <ul>
            <li>tomcat最顶层容器，代表整个服务器，一般只有一个server，server有一个或多个service，service分为两部分，connector和container</li>
            <li>service中可以有多个connector,
                用于处理requet,response,socket的转换如http和https,container用于封装和管理servlet，一个service中只有一个container
            </li>
            <li>tomcat中的server由org.apache.catalina.startup.Catalin来管理，Catalina是整个Tomcat的管理类，他里面有三个load, start, stop
                分别用来管理整个服务器的生命周期
            </li>
            <li>load方法用于
                根据conf/server.xml文件创建server并调用server的init方法进行初始化，start方法用于启动服务器，stop方法用于停止服务器地，他们分别调用server的start和stop方法
            </li>
            <li>这三个方法都会按容器的结构逐层调用相应的方法，比如server里的start方法会调用所有service中的start方法</li>
            <li>而service中的start又会调用它下面connector和container中的start方法，这样，tomcat就启动了，init和stop方法也类似</li>
            <li>另外Catalina中await方法比较特殊，它是进入一个循环，让主线程不会退出</li>
            <li>tomcat的main方法并不在Catalina中，而是在org.apache.catalina.startup.BootStrap，类似于CatalinaAdaptor，把入口和具体的管理类分开，
                可以很方便的创建出多种启动方式，每种启动方式只需要写一个相应的CatalinaAdaptor就可以了
            </li>
        </ul>
    </div>
    <hr>
    <div>
        <p>Bootstrap启动过程</p>
        <ul>
            <li>BootStrap是tomcat的入口，正常情况启动tomcat就是启用了Bootstrap的main方法，然后创建一个Catalina实例，使用反射调用load，start, stop方法</li>
            <li>
                server默认实现是org.apache.catalina.core.StandardServer,它继承LifecycleMBeanBase,LifecycyleMBeanBase又继承LifecycleBase,init和start方法就定义在这里，
                它们分别会调用initInternal和startInternal模板方法，这两个方法又循环调用了每一个service中的init和start方法
            </li>
            <li>server中还有一个await方法，监听配置文件中的端口（默认8005，SHUTDOWN），如果为-2，直接退出，如果为-1，一直循环，直到调用stop方法，如果为大于0的其它值，
                如默认的8005，会启动一个Serversocket监听，接收到的命令为配置文件中的命令，默认为SHUTDOWN
            </li>
            <li>service默认实现org.apache.catalina.core.StandardService,同样，他也继承自LifecycleMBeanBase, 执行模板方法</li>
            <li>
                StandardService中的initInternal和startInternal方法主要调用container,executors,mapperlisener,connectors中的init和start方法
            </li>
            <li>mapperlistener是mapper的监听，可以监听container容器的变化,executors是用在connectors中管理线程的线程池</li>
        </ul>
    </div>
    <hr>
    <div>
        <p>tomcat生命周期管理</p>
        <ul>
            <li>tomcat通过org.apache.catalina.Lifecycle接口统一管理生命周期，所有生命周期组件都实现它</li>
            <li>lifecycle做四件事
                <ul>
                    <li>定义13个String类型变量，如before_init,用来区分生命周期组件的事件状态</li>
                    <li>定义三个管理监听器的方法，addLifecycleListener,findLifecycleListener,removeLifesysleListener</li>
                    <li>定义4个生命周期方法,init,start,stop,destroy</li>
                    <li>定义获取当前状态的两个方法getState,getStatName</li>
                </ul>
            </li>
            <li>lifecycle默认实现是org.apache.catalina.util.LifecycleBase,所有生命周期组件都直接或间接继承它</li>
        </ul>
    </div>
    <hr>
    <div>
        <p>
            container分析
        </p>
        <ul>
            <li>container是tomcat中的容器接口， 通常使用的Servlet就封闭在其子接口Wrapper中</li>
            <li>
                container一共有四个接口，Engine,Host,Context,Wrapper,它们是逐层包含关系，一个service中只能有一个engine,engine中可以有多个host，每个host可以有多个context,
                每个context下可以有多个wrapper
            </li>
            <li>
                <p>4个容器的作用</p>
                <ul>
                    <li>engine：用来管理多个站点</li>
                    <li>host：代表一个站点，也叫虚拟主机</li>
                    <li>context：代表一个应用程序，一般会对应一个web.xml文件</li>
                    <li>wrapper：每个wrapper封闭着一个servlet</li>
                </ul>
            </li>
        </ul>
        <p>context配置的五种方法
        <ul>
            <li>在conf/server.xml文件中的context标签</li>
            <li>conf/[enginename]/[hostname]/目录下以应用命名的xml文件</li>
            <li>应用自己的/META-INF/context.xml</li>
            <li>conf/context.xml文件</li>
            <li>conf/[enginename]/[hostname]/context.xml.default文件</li>
        </ul>
        <p>其中前三个配置的是单独的应用，后两个配置的是共享的，第四个是在tomcat中共享，第五上是在host中共享。server.xml文件只有在tomcat重启的时候才会加载
            ，所以一般不推荐在它中配置context</p>
        <p>
            wrapper的配置就是我们在web.xml中配置的servlet，一个servlet对应一个wrapper,另外也可以在tomcat中的conf/web.xml中配置全局的wrapper,处理jsp的JspServlet就配置在这里</p>
        </p>
    </div>
    <div>
        <p>container启动</p>
        <ul>
            <li>container的启动是通过init和start方法来完成的</li>
            <li>只有最外层的init是service调用的，子窗口的init是在执行start方法时通过状态判断还没有初始化后才调用的</li>
            <li>
                connector收到请求后会调用顶层的pipeline，即engine的pipeline,engine管线启动后会调用它里面的每一个value,直到最后的basevalue,basevalue会调用下一层的pipeline，
                即host的pipeline,host也会以同样的方式处，context,wrapper也是如此，当warpper执行到basevalue后会创建一个filterchain,调用dofilter来处理请求，FilterChain中包含着
                我们配置的filter和servlet, 其doFilter 方法会依次调用所有Filter 的doFilter 方法和Servlet 的service 方法，这样请求就得到处理了。
            </li>
        </ul>
    </div>
</div>
<jsSection>

</jsSection>
</body>
</html>