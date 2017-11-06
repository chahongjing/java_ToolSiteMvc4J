<%@ page import="com.zjy.entities.UserInfo" %>
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
<title>学习jsp</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div>
    <pre class="inline">out.pringln(myBean.getName());</pre>
    相当于 &lt;%=
    <pre class="inline">myBean.getName()</pre>
    %&gt;
    <br>
    在第一次访问jsp页面时，引擎先把jsp预生成为一个servlet（name_jsp.java）文件，在tomcat中work目录下，后续就会像访问正常的servlet一样的方式来访问jsp<br>
    因此也可以在web.xml中配置一个servlet<br>
    &lt;servlet&gt;<br>&lt;servlet-name&gt;indexjsp&lt;/servlet-name&gt;<br>&lt;jsp-file&gt;/index.jsp&lt;/jsp-file&gt;<br>&lt;/servlet&gt;
    <br/>
    <p>include指令和标签区别
    <ul>
        <li>
            &lt;%@ include file="list.jsp"%&gt;:静态包含，只会生成一个servlet
        </li>
        <li>
            &lt;/jsp:include&gt;:动态包含，会生成两个servlet
        </li>
    </ul>
    </p>
    <br/><br/>
    <h3>九大内置对象</h3>
    <ul>
        <li>application(ServletContext)</li>
        <li>session(HttpSession)</li>
        <li>request(HttpSevletRequest)</li>
        <li>response(HttpResponse)</li>
        <li>page(this)，当前jsp页面对应的servlet对象实例，继承了HttpJspBase，HttpJspBase继承了HttpServlet,但定义为Object类型，因此不常用</li>
        <li>out(JspWriter)</li>
        <li>pageContext(PageContext)，可以直接获取到其它8个对象</li>
        <li>config(ServletConfig)</li>
        <li>exception，页面中isErrorPage=true时才可以使用， 如&lt;%@ apge isErrorPage="true" %&gt;</li>
    </ul>
    <br>
    <%
        pageContext.setAttribute("name", "PageContext");
        request.setAttribute("name", "HttpSevletRequest");
        session.setAttribute("name", "HttpSession");
        application.setAttribute("name", "ServletContext");
    %>
    其中application(<%= application.getAttribute("name") %>, 当前应用程序) &gt; session(<%= session.getAttribute("name") %>
    ，当前会话)
    &gt; request(<%= request.getAttribute("name") %>，当前请求) &gt; pageContext(<%= pageContext.getAttribute("name") %>
    ，当前页面)
    <br>他们都有
    <ul>
        <li>setAttribute方法</li>
        <li>getAttribute方法</li>
        <li>getAttributeNames方法</li>
        <li>removeAttribute方法</li>
    </ul>
    在jsp中可以定义页面类的属性，还可以定义方法, 要使用&lt;%<span class="bold red">!</span>
    <pre class="inline">int a = 1;</pre>
    %&gt;
    <br>
    &lt;%<span class="bold red">!</span>
    <pre class="inline">public void myFunc() { ... }</pre>
    %&gt;
    <br/><br/>

    <p>
    <h3>jsp指令</h3>
    <ul>
        <li>&lt;%@ page contentType="text/html;chartset=gb2312"%&gt;, 总共有page, include, taglib三种指令</li>
        <li>指令的属性名区分大小写，且指令一般放在页面起始位置</li>
        <li>page指令常用属性，每个page指令可以有<span class="red">一个或多个</span>属性
             <ul>
                 <li>session=true|false, 当前页面是否允许使用session, 默认为true，当为false时，表示当前界面禁用session隐含变量，但并不表示此页面未创建session对象</li>
                 <li>import=package.class, 页面要引入的包，可以有多条page指令进行导入</li>
                 <li>errorPage=relative_url,当发生错误时要显示的错误页面地址,内部使用的是<span class="red">转发</span>机制</li>
                 <li>isErrorPage=true|false，指明当前页面是否是错误页面,如果为true，则可以使用exception变量，此时此页面一般不可以直接访问，放在WEB-INF下，如果直接访问则exception为空</li>
                 <li>contentType=mimeType，通常是调用response.setContentType</li>
                 <li>pageEncoding=characterSet, 一般和contentType中的编码一致</li>
                 <li>isELIgnored=true|false，默认为false, 如果为true，那么\${表达式}将会原样输出</li>

                 <li>language=java</li>
                 <li>extends=package.class, 指页面继承哪个类，如果有自定义类，可以添加此属性，默认继承HttpJspBase类</li>
                 <li>buffer=none|8kb|sizekb</li>
                 <li>autoFlush=true|false</li>
                 <li>isThreadSafe=true|false</li>
                 <li>info=text</li>
             </ul>
        </li>
    </ul>
    </p>

    <p>
        <h3>jsp标签</h3>
        <br>
        &lt;jsp:include page="a.jsp"&gt;&lt;/jsp:include&gt;<br><br>
        &lt;jsp:forward page="a.jsp"&gt;<br>
        &nbsp;&nbsp;&lt;jsp:param name="" value=""&gt;<br>&nbsp;&nbsp;&lt;/jsp:param&gt;
        <br>&lt;/jsp:forward&gt;<br>相当于requestDispatcher.forward(request, response); include也可以添加jsp:param,
        用request.getParameter获取
    <br>
    <ul>
        <li>&lt;jsp:useBean&gt;表示从对应范围内获取bean，若有则直接使用，若没有，则使用反射创建一个对象返回</li>
        <li>&lt;jsp:setProperty&gt;若property为*，且省略vlaue属性，则会自动把请求参数的值填充到bean中</li>
        <li>&lt;jsp:getProperty&gt;</li>
    </ul>
    <jsp:useBean id="user" class="com.zjy.entities.UserInfo" scope="session"></jsp:useBean>
    <jsp:setProperty name="user" property="userName" value="zjy"></jsp:setProperty>
    getProperty:<jsp:getProperty name="user" property="username"></jsp:getProperty>
    </p>
    <br><br>
    <h3>中文乱码</h3>
    <ul>
        <li>pageEncoding=utf-8</li>
        <li>contentType=text/html;charset=UTF-8</li>
        <li>对于post请求，在<span class="red">获取参数之前(getParameter)</span>调用request.setCharacterEncoding("UTF-8")，才会生效</li>
        <li>对于get请求，在tomcat中conf/server.xml中找到节点Connector，添加属性useBodyEncodingForURI设置为true（此方法将使用post请求中保持一致的编码）<br>
            还可以直接设置为URIEncoding=UTF-8,这只对get有效
        </li>
    </ul>

    <h3>测试代码<a href="http://blog.csdn.net/u010168160/article/details/49182867">学习地址</a></h3>
    <jsp:useBean id="testUser" class="com.zjy.entities.UserInfo" scope="session"></jsp:useBean>
    <jsp:setProperty name="testUser" property="userName" value="曾军毅"></jsp:setProperty>
    age<%
    UserInfo user = (UserInfo) session.getAttribute("testUser");
    out.println(user.getUserName());
%>
    age
    <jsp:getProperty name="testUser" property="userName"></jsp:getProperty>
</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>

<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>